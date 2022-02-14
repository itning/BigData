package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 实现strStr()函数。
 * <p>
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author itning
 * @since 2022/2/14 9:48
 */
public class ImplementStrstrTest {
    @Test
    void implementStrstrTest() {
        Assertions.assertEquals(2, strStr("hello", "ll"));
        Assertions.assertEquals(-1, strStr("aaaaa", "bba"));
        Assertions.assertEquals(0, strStr("", ""));
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }

        a:
        for (int i = 0; i < haystack.toCharArray().length; i++) {
            for (int i1 = 0; i1 < needle.toCharArray().length; i1++) {
                try {
                    if (haystack.charAt(i + i1) != needle.charAt(i1)) {
                        continue a;
                    }
                } catch (Exception e) {
                    continue a;
                }
            }
            return i;
        }
        return -1;
    }
}
