package top.itning.disruptor.quickstart;

import com.lmax.disruptor.EventHandler;

/**
 * @author itning
 * @date 2019/5/8 17:52
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者: " + event + " sequence: " + sequence + " endOfBatch: " + endOfBatch);
    }
}
