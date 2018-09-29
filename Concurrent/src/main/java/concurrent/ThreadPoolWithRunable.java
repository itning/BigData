package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交 Runnable ，任务完成后 Future 对象返回 null
 * 调用excute,提交任务, 匿名Runable重写run方法, run方法里是业务逻辑
 *
 * @author wangn
 */
@SuppressWarnings("all")
public class ThreadPoolWithRunable {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i < 5; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
