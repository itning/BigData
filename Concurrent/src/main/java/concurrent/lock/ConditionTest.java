package concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author itning
 */
public class ConditionTest {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Produce pd = new Produce(ss);
        Consume cs = new Consume(ss);
        Thread t1 = new Thread(pd);
        Thread t2 = new Thread(cs);
        t1.start();
        t2.start();
    }
}

/**
 * 馒头实体类
 */
class ManTou {
    private int id;

    ManTou(int id) {
        this.id = id;
    }

    private int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ManTou " + getId();
    }
}

/**
 * 馒头框类
 */
class SyncStack {
    private Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();
    private int index = 0;
    private ManTou[] mtArray = new ManTou[2];

    void push(ManTou mt) {
        lock.lock();
        try {
            while (index == mtArray.length) {
                try {
                    // 队列满了，生产者挂起
                    producerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            consumerCondition.signal();
            mtArray[index] = mt;
            index++;
            System.out.println("生产了" + mt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void pop() {
        lock.lock();
        try {
            while (index == 0) {
                try {
                    // 队列没有馒头了，消费者挂起
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            producerCondition.signal();
            index--;
            System.out.println("消费了" + mtArray[index]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者
 */
class Produce implements Runnable {
    private static final int TOTAL_COUNT = 20;
    private SyncStack ss;

    Produce(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < TOTAL_COUNT; i++) {
            ManTou mt = new ManTou(i);
            if (ss != null) {
                ss.push(mt);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

/**
 * 消费者
 */
class Consume implements Runnable {
    private static final int TOTAL_COUNT = 20;
    private SyncStack ss;

    Consume(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < TOTAL_COUNT; i++) {
            if (ss != null) {
                ss.pop();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}