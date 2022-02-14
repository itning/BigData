package top.itning.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author itning
 * @since 2022/2/14 10:51
 */
public class PlusOneTest {
    @Test
    void plusOneTest() {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 4})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
    }

    public int[] plusOne(int[] digits) {
        if (digits.length == 1 && digits[0] == 0) {
            digits[0] = 1;
            return digits;
        }
        int less = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (less != 0) {
                less = 0;
            }
            if (digits[i] + 1 == 10) {
                digits[i] = 0;
                less = 1;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if (less == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, result.length - 1);
            return result;
        }
        return digits;
    }
}
