package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * Lock 模型解决了 synchronized 关键字的不足<br/>
 * 一个线程获取到锁另一个需要获取同一把锁的线程无需一直等待<br/>
 * Lock必须手动释放锁
 *
 * @author itning
 */
@SuppressWarnings("all")
public class LockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(2, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //手动释放锁
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(2, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
