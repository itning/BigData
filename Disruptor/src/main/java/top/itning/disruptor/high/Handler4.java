package top.itning.disruptor.high;


import com.lmax.disruptor.EventHandler;

/**
 * @author itning
 * @date 2019/5/10 8:50
 */
public class Handler4 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者4: " + event + " sequence: " + sequence + " endOfBatch: " + endOfBatch);
    }
}
