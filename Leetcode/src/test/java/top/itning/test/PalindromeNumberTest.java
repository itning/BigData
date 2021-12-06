package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * @author itning
 * @since 2021/12/6 19:37
 */
public class PalindromeNumberTest {
    @Test
    void palindromeNumberTest() {
        Assertions.assertTrue(isPalindrome(121));
        Assertions.assertFalse(isPalindrome(1211));
    }

    public boolean isPalindrome(int x) {
        return isPalindromic(String.valueOf(x));
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
