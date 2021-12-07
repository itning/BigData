package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author itning
 * @since 2021/12/7 14:27
 */
public class LongestCommonPrefixTest {
    @Test
    void longestCommonPrefixTest() {
        Assertions.assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        Assertions.assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        Assertions.assertEquals("", longestCommonPrefix(new String[]{"", "racecar", "car"}));
    }

    public String longestCommonPrefix(String[] strs) {
        // 1 <= strs.length <= 200
        // 0 <= strs[i].length <= 200
        // strs[i] 仅由小写英文字母组成
        int l = Integer.MAX_VALUE;
        for (String str : strs) {
            l = Math.min(l, str.length());
        }
        if (l == Integer.MAX_VALUE || l == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        a:
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < strs.length; j++) {
                char c = strs[j].charAt(i);
                if (j + 1 != strs.length) {
                    char cc = strs[j + 1].charAt(i);
                    if (cc != c) {
                        break a;
                    }
                }
            }
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }
}
