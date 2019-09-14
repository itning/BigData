package concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建持有足够线程的线程池来支持给定的并行级别，
 * 并通过使用多个队列，减少竞争，它需要穿一个并行级别的参数，
 * 如果不传，则被设定为默认的CPU数量(Runtime.getRuntime().availableProcessors())。
 *
 * @author itning
 */
public class WorkStealingPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool(4);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

        Thread.sleep(5000);
        executorService.shutdown();
    }
}
