package concurrent.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * 自增计数求和操作
 *
 * @author itning
 * @date 2020/5/22 19:32
 * @see java.util.concurrent.atomic.LongAdder
 * @see java.util.concurrent.atomic.DoubleAdder
 */
public class AdderTest {
    private static final LongAdder LONG_ADDER = new LongAdder();

    public static void main(String[] args) {
        LONG_ADDER.increment();
        LONG_ADDER.add(5L);
        // will call sum()
        System.out.println(LONG_ADDER.longValue());
        System.out.println(LONG_ADDER.sum());
        System.out.println(LONG_ADDER.sumThenReset());
    }
}
