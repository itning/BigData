package 适配器模式.task;

import java.util.concurrent.Callable;

/**
 * @author itning
 * @since 2021/12/22 10:16
 */
public class Task implements Callable<Long> {
    private long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() throws Exception {
        long r = 0;
        for (long n = 1; n <= this.num; n++) {
            r = r + n;
        }
        System.out.println("Result: " + r);
        return r;
    }
}