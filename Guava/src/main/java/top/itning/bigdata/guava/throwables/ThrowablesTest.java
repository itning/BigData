package top.itning.bigdata.guava.throwables;

import com.google.common.base.Throwables;
import org.junit.Test;

/**
 * @author itning
 * @see Throwables
 */
public class ThrowablesTest {
    @Test
    public void test() {
        //有时候，你会想把捕获到的异常再次抛出。
        //这种情况通常发生在Error或RuntimeException被捕获的时候，
        //你没想捕获它们，但是声明捕获Throwable和Exception的时候，
        //也包括了了Error或RuntimeException。Guava提供了若干方法，来判断异常类型并且重新传播异常。
    }
}
