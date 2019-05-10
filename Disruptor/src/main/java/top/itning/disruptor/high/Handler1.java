package top.itning.disruptor.high;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author itning
 * @date 2019/5/10 8:50
 */
public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者1: " + event + " sequence: " + sequence + " endOfBatch: " + endOfBatch);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("消费者1: " + event);
    }
}
