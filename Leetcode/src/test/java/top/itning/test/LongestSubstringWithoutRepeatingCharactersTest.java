package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author itning
 * @since 2021/12/6 19:00
 */
public class LongestSubstringWithoutRepeatingCharactersTest {
    @Test
    void longestSubstringWithoutRepeatingCharactersTest() {
        Assertions.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);
            Integer index = map.get(item);
            if (index != null) {
                for (int j = left; j < index; j++) {
                    map.remove(s.charAt(j));
                }
                left = index + 1;
                map.put(item, i);
            } else {
                map.put(item, i);
                length = Math.max(length, i - left + 1);
            }
        }
        return length;
    }
}
