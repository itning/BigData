package top.itning.recursive;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 *
 * @author itning
 * @date 2019/7/7 20:14
 */
public class Solution {
    public static void main(String[] args) {
        int fibonacci = new Solution().Fibonacci(4);
        System.out.println(fibonacci);
    }

    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        return f(n);
    }

    /**
     * F(n)=F(n-1)+F(n-2)
     *
     * @param n n
     * @return F(n)
     */
    private int f(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }
}
