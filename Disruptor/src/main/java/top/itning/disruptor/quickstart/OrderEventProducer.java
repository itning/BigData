package top.itning.disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author itning
 * @date 2019/5/8 18:07
 */
public class OrderEventProducer {
    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer byteBuffer) {
        //1.在生产者发送消息的时候,首先需要从我们的ringBuffer获取一个可用的序号
        long sequence = ringBuffer.next();
        try {
            //2.根据这个序号,找到具体的 OrderEvent 元素
            //此时OrderEvent对象是刚被初始化的对象
            OrderEvent orderEvent = ringBuffer.get(sequence);
            //3.赋值
            orderEvent.setPrice(byteBuffer.getLong(0));
        } finally {
            //4.提交操作
            ringBuffer.publish(sequence);
        }
    }
}
