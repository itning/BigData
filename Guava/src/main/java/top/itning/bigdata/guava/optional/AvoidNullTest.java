package top.itning.bigdata.guava.optional;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Set;

/**
 * 避免空指针
 * 大多数情况下，开发人员使用null表明的是某种缺失情形：
 * 可能是已经有一个默认值，或没有值，或找不到值。
 * 例如，Map.get返回null就表示找不到给定键对应的值。
 *
 * @author itning
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class AvoidNullTest {
    @Test
    public void testOptional() {
        //创建引用缺失的Optional实例
        Optional.<String>absent();
        //创建指定引用的Optional实例，若引用为null则表示缺失
        Optional.fromNullable("");
        //创建指定引用的Optional实例，若引用为null则抛出NullPointerException
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // 引用存在,返回true
        possible.get(); // 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        Set<Integer> set = possible.asSet();
        Integer or = possible.or(6);
    }
}
