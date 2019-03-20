package top.itning.bigdata.guava.objects;

import org.junit.Test;

/**
 * @author itning
 */
public class ObjectsTest {
    @Test
    public void test() {
        //当一个对象中的字段可以为null时，实现Object.equals方法会很痛苦，因为不得不分别对它们进行null检查。
        //使用Objects.equal帮助你执行null敏感的equals判断，从而避免抛出NullPointerException。例如:
        com.google.common.base.Objects.equal("a", "a"); // returns true
        com.google.common.base.Objects.equal(null, "a"); // returns false
        com.google.common.base.Objects.equal("a", null); // returns false
        com.google.common.base.Objects.equal(null, null); // returns true
        //注意：JDK7引入的Objects类提供了一样的方法Objects.equals。

        //hashCode
        //用对象的所有字段作散列[hash]运算应当更简单。Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。
        // 你可以使用Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。
        //注意：JDK7引入的Objects类提供了一样的方法Objects.hash(Object...)

        //toString

        //好的toString方法在调试时是无价之宝，但是编写toString方法有时候却很痛苦。
        //使用 Objects.toStringHelper可以轻松编写有用的toString方法。例如：
        java.util.Objects.toString("");
    }
}
