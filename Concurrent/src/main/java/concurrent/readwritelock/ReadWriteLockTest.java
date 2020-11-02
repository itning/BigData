package concurrent.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁<br/>
 * 读可以一起读,但是有写锁必须等待写锁释放才可进行读写<br/>
 * 一个线程已经在读,其它线程想写必须等待读锁释放<br/>
 *
 * @author itning
 */
@SuppressWarnings("all")
public class ReadWriteLockTest {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    read();
                    write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    write();
                    read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    read();
                    write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    write();
                    read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static void write() throws InterruptedException {
        reentrantReadWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " write");
        Thread.sleep(2000);
        reentrantReadWriteLock.writeLock().unlock();
    }

    private static void read() throws InterruptedException {
        reentrantReadWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " read");
        Thread.sleep(2000);
        reentrantReadWriteLock.readLock().unlock();
    }
}
