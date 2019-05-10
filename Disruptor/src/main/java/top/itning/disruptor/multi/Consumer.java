package top.itning.disruptor.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author itning
 * @date 2019/5/10 9:42
 */
public class Consumer implements WorkHandler<Order> {
    private String consumerId;
    private static AtomicInteger count = new AtomicInteger(0);
    private Random random = new Random();

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(Order event) throws Exception {
        Thread.sleep(random.nextInt(5));
        System.out.println("consumerId: " + consumerId + event);
    }

    public int getCount() {
        return count.get();
    }
}
