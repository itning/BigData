package concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author itning
 */
public class AQSTest {
    public static void main(String[] args) {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
            @Override
            protected boolean tryAcquire(int arg) {
                //尝试获取资源
                return super.tryAcquire(arg);
            }

            @Override
            protected boolean tryRelease(int arg) {
                //尝试释放资源
                return super.tryRelease(arg);
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
        };
    }
}
