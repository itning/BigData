package top.itning.bigdata.guava.collections;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.awt.*;

/**
 * 不可变集合
 *
 * @author itning
 * @see ImmutableCollection
 */
public class ImmutableCollectionsTest {
    private static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

    private static final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet.<Color>builder()
            //.addAll(WEBSAFE_COLORS)
            .add(new Color(0, 191, 255))
            .build();


    @Test
    public void test() {
        //will throw UnsupportedOperationException;
        COLOR_NAMES.add("");
    }
}
