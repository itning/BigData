package concurrent.threadpool;

import java.util.concurrent.*;

/**
 * 创建持有足够线程的线程池来支持给定的并行级别，
 * 并通过使用多个队列，减少竞争，它需要穿一个并行级别的参数，
 * 如果不传，则被设定为默认的CPU数量(Runtime.getRuntime().availableProcessors())。
 *
 * @author itning
 */
public class ForkJoinWorkStealingPoolTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*ExecutorService executorService = Executors.newWorkStealingPool(4);
        executorService.submit(() -> System.out.println(Thread.currentThread().getName()));
        Thread.sleep(5000);
        executorService.shutdown();*/

        ForkJoinPool forkJoinPool = new ForkJoinPool(
                Runtime.getRuntime().availableProcessors(),
                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                // exception invoke handler
                null,
                true
        );
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new IntSum(1, 2, 3, 4, 5));
        Integer integer = forkJoinTask.get();
        System.out.println(integer);
    }
}

class IntSum extends RecursiveTask<Integer> {
    private final int start;
    private final int end;
    private final int[] nums;

    public IntSum(int... nums) {
        this(0, nums.length - 1, nums);
    }

    private IntSum(int start, int end, int... nums) {
        this.start = start;
        this.end = end;
        this.nums = nums;
    }

    @Override
    protected Integer compute() {
        int count = end - start;
        if (count < 0) {
            System.out.println(Thread.currentThread().getName() + " count < 0");
            return 0;
        } else if (count == 0) {
            System.out.println(Thread.currentThread().getName() + " count = 0; nums[start] = " + nums[start]);
            return nums[start];
        } else {
            int x = (start + end) / 2;
            System.out.println(Thread.currentThread().getName() + " 拆分任务 start:" + start + " end:" + end + " x:" + x);
            IntSum a = new IntSum(start, x, nums);
            IntSum b = new IntSum(x + 1, end, nums);
            a.fork();
            b.fork();
            return a.join() + b.join();
        }
    }
}
