package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 12. 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 *
 * @author itning
 * @since 2021/12/7 10:31
 */
public class IntegerToRomanTest {
    @Test
    void integerToRomanTest() {
        Assertions.assertEquals("X", intToRoman(10));
        Assertions.assertEquals("XII", intToRoman(12));
        Assertions.assertEquals("XV", intToRoman(15));
        Assertions.assertEquals("MCXXIII", intToRoman(1123));
        Assertions.assertEquals("MCMXCIV", intToRoman(1994));
    }

    public String intToRoman(int num) {
        // 1 <= num <= 3999
        switch (num) {
            case 4:
                return "IV";
            case 5:
                return "V";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 40:
                return "XL";
            case 50:
                return "L";
            case 90:
                return "XC";
            case 100:
                return "C";
            case 400:
                return "CD";
            case 500:
                return "D";
            case 900:
                return "CM";
            case 1000:
                return "M";
            default: {
                StringBuilder sb = new StringBuilder();
                if (num > 1000) {
                    int i = num / 1000;
                    repeatString('M', i, sb);
                    num = num % 1000;
                }
                if (num >= 900) {
                    sb.append("CM");
                    num -= 900;
                }
                if (num >= 500) {
                    int i = num / 500;
                    repeatString('D', i, sb);
                    num = num % 500;
                }
                if (num >= 400) {
                    sb.append("CD");
                    num -= 400;
                }
                if (num >= 100) {
                    int i = num / 100;
                    repeatString('C', i, sb);
                    num = num % 100;
                }
                if (num >= 90) {
                    sb.append("XC");
                    num -= 90;
                }
                if (num >= 50) {
                    int i = num / 50;
                    repeatString('L', i, sb);
                    num = num % 50;
                }
                if (num >= 40) {
                    sb.append("XL");
                    num -= 40;
                }
                if (num >= 10) {
                    int i = num / 10;
                    repeatString('X', i, sb);
                    num = num % 10;
                }
                if (num >= 9) {
                    sb.append("IX");
                    num -= 9;
                }
                if (num >= 5) {
                    int i = num / 5;
                    repeatString('V', i, sb);
                    num = num % 5;
                }
                if (num >= 4) {
                    sb.append("IV");
                    num -= 4;
                }
                repeatString('I', num, sb);
                return sb.toString();
            }
        }
    }

    private void repeatString(char c, int num, StringBuilder sb) {
        for (int i = 0; i < num; i++) {
            sb.append(c);
        }
    }
}
