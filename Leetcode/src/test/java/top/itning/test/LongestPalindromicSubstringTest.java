package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * @author itning
 * @since 2021/12/6 11:10
 */
public class LongestPalindromicSubstringTest {
    @Test
    void longestPalindromeTest() {
        Assertions.assertEquals("bb", longestPalindrome("cbbd"));
        Assertions.assertEquals("bab", longestPalindrome("babad"));
        Assertions.assertEquals("a", longestPalindrome("a"));
        Assertions.assertEquals("a", longestPalindrome("ac"));
    }

    public String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
