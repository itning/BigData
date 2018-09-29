package concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly 可中断的锁
 *
 * @author wangn
 */
@SuppressWarnings("all")
public class LockInterruptibly {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "线程被中断");
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "线程被中断");
                } finally {
                    lock.unlock();
                }
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        //中断
        thread1.interrupt();
    }
}
