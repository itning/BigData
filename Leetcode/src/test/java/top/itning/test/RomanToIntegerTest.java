package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 *
 * @author itning
 * @since 2021/12/7 11:36
 */
public class RomanToIntegerTest {
    @Test
    void romanToIntegerTest() {
        Assertions.assertEquals(10, romanToInt("X"));
        Assertions.assertEquals(12, romanToInt("XII"));
        Assertions.assertEquals(15, romanToInt("XV"));
        Assertions.assertEquals(1123, romanToInt("MCXXIII"));
        Assertions.assertEquals(1994, romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        switch (s) {
            case "IV":
                return 4;
            case "V":
                return 5;
            case "IX":
                return 9;
            case "X":
                return 10;
            case "XL":
                return 40;
            case "L":
                return 50;
            case "XC":
                return 90;
            case "C":
                return 100;
            case "CD":
                return 400;
            case "D":
                return 500;
            case "CM":
                return 900;
            case "M":
                return 1000;
            default: {
                int result = 0;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c == 'M') {
                        result += 1000;
                    } else if (c == 'D') {
                        result += 500;
                    } else if (c == 'C') {
                        if (i + 1 != s.length()) {
                            String n = new String(new char[]{c, s.charAt(i + 1)});
                            if (n.equals("CM")) {
                                result += 900;
                                i++;
                            } else if (n.equals("CD")) {
                                result += 400;
                                i++;
                            } else {
                                result += 100;
                            }
                        } else {
                            result += 100;
                        }
                    } else if (c == 'L') {
                        result += 50;
                    } else if (c == 'X') {
                        if (i + 1 != s.length()) {
                            String n = new String(new char[]{c, s.charAt(i + 1)});
                            if (n.equals("XC")) {
                                result += 90;
                                i++;
                            } else if (n.equals("XL")) {
                                result += 40;
                                i++;
                            } else {
                                result += 10;
                            }
                        } else {
                            result += 10;
                        }
                    } else if (c == 'V') {
                        result += 5;
                    } else if (c == 'I') {
                        if (i + 1 != s.length()) {
                            String n = new String(new char[]{c, s.charAt(i + 1)});
                            if (n.equals("IX")) {
                                result += 9;
                                i++;
                            } else if (n.equals("IV")) {
                                result += 4;
                                i++;
                            } else {
                                result += 1;
                            }
                        } else {
                            result += 1;
                        }
                    } else {
                        throw new IllegalArgumentException(String.valueOf(c));
                    }
                }
                return result;
            }
        }
    }
}
