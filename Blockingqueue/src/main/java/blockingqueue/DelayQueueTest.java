package blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟阻塞队列
 *
 * @author itning
 */
public class DelayQueueTest {
    public static void main(String[] args) throws Exception {
        DelayQueue<Task> delayQueue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        delayQueue.put(new Task(now + 3000));
        delayQueue.put(new Task(now + 4000));
        delayQueue.put(new Task(now + 6000));
        delayQueue.put(new Task(now + 1000));
        System.out.println(delayQueue);

        for (int i = 0; i < 4; i++) {
            System.out.println(delayQueue.take());
        }

    }

    static class Task implements Delayed {
        long time;

        Task(long time) {
            this.time = time;
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "" + time;
        }
    }
}
