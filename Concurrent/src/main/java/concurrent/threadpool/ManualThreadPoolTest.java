package concurrent.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 手动创建线程池
 *
 * @author itning
 */
public class ManualThreadPoolTest {
    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                //核心线程池大小
                0,
                //线程池最大线程数
                Integer.MAX_VALUE,
                //表示线程没有任务执行时最多保持多久时间会终止，然后线程池的数目维持在corePoolSize 大小
                60L,
                //参数keepAliveTime的时间单位
                TimeUnit.SECONDS,
                //一个阻塞队列，用来存储等待执行的任务，如果当前对线程的需求超过了corePoolSize大小，才会放在这里
                new SynchronousQueue<>(),
                //线程工厂，主要用来创建线程，比如可以指定线程的名字
                namedThreadFactory,
                //如果线程池已满，新的任务的处理方式
                new RejectedExecutionHandler() {

                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //
                    }
                });
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }
}
