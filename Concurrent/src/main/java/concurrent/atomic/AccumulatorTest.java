package concurrent.atomic;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * 可以实现求和，最大值，最小值等等
 *
 * @author itning
 * @date 2020/5/22 19:40
 * @see java.util.concurrent.atomic.LongAccumulator
 * @see java.util.concurrent.atomic.DoubleAccumulator
 */
public class AccumulatorTest {
    private static final LongAccumulator LONG_ACCUMULATOR = new LongAccumulator(Long::max, 5L);

    public static void main(String[] args) {
        LONG_ACCUMULATOR.accumulate(1L);
        LONG_ACCUMULATOR.accumulate(6L);
        LONG_ACCUMULATOR.accumulate(12L);
        LONG_ACCUMULATOR.accumulate(8L);
        System.out.println(LONG_ACCUMULATOR.get());
    }
}
