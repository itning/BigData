package concurrent.thread;

/**
 * 输出 12A 34B 56C 78D 910E 1112F 1314G 1516H...5152Z
 * 当线程执行wait()时，会把当前的锁释放，然后让出CPU，进入等待状态。
 * 当执行notify/notifyAll方法时，会唤醒一个处于等待该 对象锁 的线程，
 * 然后继续往下执行，直到执行完退出对象锁锁住的区域（synchronized修饰的代码块）后再释放锁。
 *
 * @author itning
 */
public class NotIfyWaitTest {
    private static final Object O = new Object();
    private static volatile boolean start = true;

    public static void main(String[] args) {
        new ThreadB().start();
        new ThreadA().start();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (O) {
                start = false;
                int count = 0;
                for (int i = 1; i < 53; i++) {
                    System.out.print(i);
                    count++;
                    if (count == 2) {
                        try {
                            O.notify();
                            O.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            count = 0;
                        }
                    }

                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (O) {
                if (start) {
                    try {
                        // 必须让打印数字的线程先执行
                        O.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 'A'; i <= 'Z'; i++) {
                    try {
                        System.out.print((char) i + " ");
                        O.notify();
                        if (i != 'Z') {
                            O.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
