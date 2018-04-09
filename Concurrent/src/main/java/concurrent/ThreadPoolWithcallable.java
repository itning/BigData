package concurrent;

import java.util.concurrent.*;

/**
 * 提交 Callable，该方法返回一个 Future 实例表示任务的状态
 * 调用submit提交任务, 匿名Callable,重写call方法, 有返回值, 获取返回值会阻塞,一直要等到线程任务返回结果
 *
 * @author wangn
 */
public class ThreadPoolWithcallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("当前CPU核数:" + processors);
        ExecutorService pool = Executors.newFixedThreadPool(processors);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            Future<String> submit = pool.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String s = Thread.currentThread().getName();
                    System.out.println(s);
                    return s + "--" + finalI + "返回消息";
                }
            });
            //获取返回的消息会阻塞,直到获取到
            System.out.println(submit.get());
        }
        pool.shutdown();
    }
}
