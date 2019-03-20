package top.itning.bigdata.guava.precondition;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 前置条件
 * Guava在Preconditions类中提供了若干前置条件判断的实用方法
 * 每个方法都有三个变种：
 * 没有额外参数：抛出的异常中没有错误消息；
 * 有一个Object对象作为额外参数：抛出的异常使用Object.toString() 作为错误消息；
 * 有一个String对象作为额外参数，并且有一组任意数量的附加Object对象：这个变种处理异常消息的方式有点类似printf，但考虑GWT的兼容性和效率，只支持%s指示符。
 *
 * @author itning
 * @see Preconditions
 */
public class PreconditionsTest {
    @Test
    public void test() {
        int i = new Random().nextInt();
        int j = new Random().nextInt();
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
    }
}
