package concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask创建线程
 *
 * @author itning
 */
public class FutureTaskTest implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new FutureTaskTest());
        Thread thread = new Thread(futureTask, "线程1");
        thread.start();
        System.out.println(futureTask.get());
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return Thread.currentThread().getName() + "返回结果";
    }
}
