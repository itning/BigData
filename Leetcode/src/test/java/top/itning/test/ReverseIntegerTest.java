package top.itning.test;

import org.junit.jupiter.api.Test;

/**
 * @author itning
 * @since 2021/12/6 14:13
 */
public class ReverseIntegerTest {
    @Test
    void reverseIntegerTest() {
        reverse(-123);
    }

    public int reverse(int x) {
        String s = new StringBuilder(String.valueOf(Math.abs(x))).reverse().toString();
        if (x < 0) {
            s = "-" + s;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
