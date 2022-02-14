package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * @author itning
 * @since 2022/2/14 10:42
 */
public class LengthOfLastWordTest {
    @Test
    void lengthOfLastWordTest() {
        Assertions.assertEquals(5, lengthOfLastWord("Hello World"));
        Assertions.assertEquals(4, lengthOfLastWord("   fly me   to   the moon  "));
    }

    public int lengthOfLastWord(String s) {
        int length = 0;
        for (int i = s.toCharArray().length - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (length != 0) {
                    break;
                }
            } else {
                length++;
            }
        }
        return length;
    }
}
