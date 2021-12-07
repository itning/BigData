package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * @author itning
 * @since 2021/12/6 15:10
 */
public class StringToIntegerAtoiTest {

    @Test
    void stringToIntegerAtoiTest() {
        Assertions.assertEquals(-123, myAtoi("-0123"));
        Assertions.assertEquals(123, myAtoi("0123"));
        Assertions.assertEquals(0, myAtoi("aa00010123aa"));
        Assertions.assertEquals(0, myAtoi("words and 987"));
        Assertions.assertEquals(3, myAtoi("3.14159"));
        Assertions.assertEquals(0, myAtoi("-+12"));
        Assertions.assertEquals(4193, myAtoi("4193 with words"));
        Assertions.assertEquals(1, myAtoi("+1"));
        Assertions.assertEquals(0, myAtoi("+"));
        Assertions.assertEquals(0, myAtoi("    0000000000000   "));
        Assertions.assertEquals(-2147483647, myAtoi("-2147483647"));
        Assertions.assertEquals(-2147483648, myAtoi("-91283472332"));
        Assertions.assertEquals(2147483647, myAtoi("21474836460"));
        Assertions.assertEquals(-2147483648, myAtoi("-2147483648"));
    }

    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            if (s.equals("+") || s.equals("-")) {
                return 0;
            }
            return s.charAt(0) - 48;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && (c == '-' || c == '+')) {
                if (c == '-') {
                    sb.append(c);
                }
                continue;
            }
            if (c >= 48 && c <= 57) {
                // 是数字
                // 数字0
                if (c == 48) {
                    if (sb.length() == 0) {
                        continue;
                    }
                    if (sb.length() == 1 && sb.toString().equals("-")) {
                        continue;
                    }
                }
                sb.append(c);
            } else {
                if (sb.length() == 0) {
                    return 0;
                }
                if (c == '.') {
                    break;
                }
                if (sb.length() > 1) {
                    break;
                }
                return 0;
            }
        }
        if (sb.length() == 0) {
            return 0;
        }
        int res = 0;
        boolean f = false;
        String n = sb.toString();
        if (sb.charAt(0) == '-') {
            f = true;
            n = sb.substring(1);
        }


        for (int i = 0; i < n.length(); i++) {
            double pow = Math.pow(10, n.length() - i - 1);
            res += (n.charAt(i) - 48) * pow;
            if (res == Integer.MAX_VALUE && i != n.length() - 1) {
                if (f) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        if (n.equals("2147483648") && f) {
            return Integer.MIN_VALUE;
        }
        if (res == Integer.MAX_VALUE && f) {
            return -2147483647;
        }
        return f ? res - (res * 2) : res;
    }
}
