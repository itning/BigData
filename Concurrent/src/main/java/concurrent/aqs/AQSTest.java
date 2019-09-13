package concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author itning
 */
public class AQSTest {
    public static void main(String[] args) {
        WC wc = new WC();
        People p1 = new People(wc);
        People p2 = new People(wc);
        People p3 = new People(wc);
        People p4 = new People(wc);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

    static class People extends Thread {
        private WC wc;

        People(WC wc) {
            this.wc = wc;
        }

        @Override
        public void run() {
            if (wc.tryEnter()) {
                System.out.println(Thread.currentThread().getName() + " OK");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    wc.exit();
                }
            }
        }
    }

    static final class WC {
        AQS aqs;

        WC() {
            this.aqs = new AQS();
        }

        private class AQS extends AbstractQueuedSynchronizer {
            @Override
            protected boolean isHeldExclusively() {
                return Thread.currentThread() == getExclusiveOwnerThread();
            }

            @Override
            protected boolean tryAcquire(int arg) {
                //尝试获取资源
                Thread thread = Thread.currentThread();
                int state = getState();
                if (state == 0) {
                    //判断队列是否有数据，有数据就返回获取锁失败(公平锁才会这么做)
                    if (hasQueuedPredecessors()) {
                        return false;
                    }
                    if (!compareAndSetState(0, state + arg)) {
                        return false;
                    }
                    //设置独占线程
                    setExclusiveOwnerThread(thread);
                    return true;
                }
                if (isHeldExclusively()) {
                    setState(state + arg);
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(int arg) {
                //尝试释放资源
                int newState = getState() - arg;
                if (newState == 0) {
                    setExclusiveOwnerThread(null);
                }
                setState(Math.max(newState, 0));
                return getState() == 0;
            }

            @Override
            protected int tryAcquireShared(int arg) {
                //尝试获取贡献资源
                return super.tryAcquireShared(arg);
            }

            @Override
            protected boolean tryReleaseShared(int arg) {
                //尝试释放共享资源
                return super.tryReleaseShared(arg);
            }

        }

        /**
         * 尝试进入
         */
        boolean tryEnter() {
            //尝试获取锁 如果失败则入队列
            aqs.acquire(1);
            return aqs.isHeldExclusively();
        }

        /**
         * 尝试退出
         */
        void exit() {
            aqs.release(1);
        }
    }
}
