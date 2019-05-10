package top.itning.disruptor.high;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author itning
 * @date 2019/5/10 8:33
 */
public class TradePushlisher implements Runnable {
    private CountDownLatch countDownLatch;
    private Disruptor<Trade> disruptor;

    public TradePushlisher(CountDownLatch countDownLatch, Disruptor<Trade> disruptor) {
        this.countDownLatch = countDownLatch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
        for (int i = 0; i < 100; i++) {
            disruptor.publishEvent(tradeEventTranslator);
        }
        countDownLatch.countDown();
    }

    class TradeEventTranslator implements EventTranslator<Trade> {

        @Override
        public void translateTo(Trade event, long sequence) {
            event.setPrice(new Random().nextDouble() * 9999);
        }
    }
}
