package top.itning.bigdata.guava.ordering;

import com.google.common.collect.Ordering;
import org.junit.Test;

/**
 * 排序
 *
 * @author itning
 */
public class OrderingTest {
    @Test
    public void test() {
        //创建排序器：常见的排序器可以由下面的静态方法创建
        //对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering<Comparable> natural = Ordering.natural();
        //按对象的字符串形式做字典排序[lexicographical ordering]
        Ordering<Object> objectOrdering = Ordering.usingToString();
        //把给定的Comparator转化为排序器
        //Ordering.from();
    }
}
