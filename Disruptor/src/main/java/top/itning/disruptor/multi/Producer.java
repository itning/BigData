package top.itning.disruptor.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * @author itning
 * @date 2019/5/10 10:08
 */
public class Producer {
    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String uuid) {
        long sequence = ringBuffer.next();
        try {
            Order order = ringBuffer.get(sequence);
            order.setId(uuid);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
