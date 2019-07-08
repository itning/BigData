package top.itning.bit;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * @author itning
 * @date 2019/7/7 21:24
 */
public class Solution {
    public static void main(String[] args) {
        int i = new Solution().NumberOf1(-12);
        System.out.println(i);
    }

    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            ++count;
        }//while
        return count;
    }
}
