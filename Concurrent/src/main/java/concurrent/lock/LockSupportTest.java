package concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>{@link LockSupport#park()}：
 * 阻塞当前线程，如果调用unpark方法或者当前线程被中断，从能从park()方法中返回
 *
 * <p>{@link LockSupport#park(Object)}：
 * 功能同方法1，入参增加一个Object对象，用来记录导致线程阻塞的阻塞对象，方便进行问题排查；
 *
 * <p>{@link LockSupport#parkNanos(long)}：
 * 阻塞当前线程，最长不超过nanos纳秒，增加了超时返回的特性；
 *
 * <p>{@link LockSupport#parkNanos(Object, long)}：
 * 功能同方法3，入参增加一个Object对象，用来记录导致线程阻塞的阻塞对象，方便进行问题排查；
 *
 * <p>{@link LockSupport#parkUntil(long)}：
 * 阻塞当前线程，知道deadline；
 *
 * <p>{@link LockSupport#parkUntil(Object, long)}：
 * 功能同方法5，入参增加一个Object对象，用来记录导致线程阻塞的阻塞对象，方便进行问题排查；
 *
 * @author itning
 * @date 2020/5/23 12:51
 * @see java.util.concurrent.locks.LockSupport
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
