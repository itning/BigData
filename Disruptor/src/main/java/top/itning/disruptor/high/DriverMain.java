package top.itning.disruptor.high;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author itning
 * @date 2019/5/9 21:32
 */
public class DriverMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Disruptor<Trade> disruptor = new Disruptor<>(
                Trade::new,
                1024 * 1024,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());

        //串行操作
        /*disruptor
                .handleEventsWith(new Handler1())
                .handleEventsWith(new Handler2())
                .handleEventsWith(new Handler3())
                .handleEventsWith(new Handler4())
                .handleEventsWith(new Handler5());*/

        //并行操作
        /*disruptor.handleEventsWith(new Handler1());
        disruptor.handleEventsWith(new Handler2());
        disruptor.handleEventsWith(new Handler3());
        disruptor.handleEventsWith(new Handler4());
        disruptor.handleEventsWith(new Handler5());*/

        //并行操作写法2
        /*disruptor.handleEventsWith(new Handler1(), new Handler2(),
                new Handler3(), new Handler4(), new Handler5());*/

        //菱形操作（有并行有串行）
        //1 2 4 5并行 3 串行
        disruptor.handleEventsWith(new Handler1(), new Handler2())
                .handleEventsWith(new Handler3())
                .handleEventsWith(new Handler4(), new Handler5());

        RingBuffer<Trade> ringBuffer = disruptor.start();

        es.submit(new TradePushlisher(countDownLatch, disruptor));

        countDownLatch.await();
        es.shutdown();
        disruptor.shutdown();

    }
}
