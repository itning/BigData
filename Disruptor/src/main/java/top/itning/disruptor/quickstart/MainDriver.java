package top.itning.disruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author itning
 * @date 2019/5/8 17:54
 */
public class MainDriver {
    public static void main(String[] args) {
        //1.实例化Disruptor对象
        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                //消息工厂对象
                orderEventFactory,
                //容器大小
                ringBufferSize,
                //线程池
                executorService,
                //单生产者
                ProducerType.SINGLE,
                //等待策略
                new BlockingWaitStrategy());
        //2.添加消费者监听
        disruptor.handleEventsWith(new OrderEventHandler());
        //3.启动
        disruptor.start();
        //4.获取实际存储数据的容器 RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer orderEventProducer = new OrderEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0L; i < 100L; i++) {
            byteBuffer.putLong(0, i);
            orderEventProducer.sendData(byteBuffer);
        }

        disruptor.shutdown();
        executorService.shutdown();
    }
}
