package top.itning.disruptor.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author itning
 * @date 2019/5/10 9:39
 */
public class DriverMain {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.MULTI,
                Order::new,
                1024 * 1024,
                new YieldingWaitStrategy());
        //创建屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        //构建多消费者
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("C" + i);
        }

        WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new OrderExceptionHandler(), consumers);
        //设置多个消费者序号
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            Producer producer = new Producer(ringBuffer);
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    producer.sendData(UUID.randomUUID().toString());
                }
            }).start();
        }
        Thread.sleep(2000);
        countDownLatch.countDown();
        Thread.sleep(8000);
    }

    static class OrderExceptionHandler implements ExceptionHandler<Order> {


        @Override
        public void handleEventException(Throwable ex, long sequence, Order event) {

        }

        @Override
        public void handleOnStartException(Throwable ex) {

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {

        }
    }
}
