package 适配器模式.task;

import java.util.concurrent.Callable;

/**
 * @author itning
 * @since 2021/12/22 10:17
 */
public class Test {
    public static void main(String[] args) {
        Callable<Long> callable = new Task(123450000L);
        // RunnableAdapter适配器做了适配
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
    }
}
