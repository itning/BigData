package top.itning.bigdata.guava.reflection;

import com.google.common.reflect.TypeToken;
import org.junit.Test;

/**
 * @author itning
 */
public class ReflectionTest {
    @Test
    public void test() {
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
    }
}
