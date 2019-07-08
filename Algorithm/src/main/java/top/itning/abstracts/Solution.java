package top.itning.abstracts;

import java.util.Stack;
import java.util.TreeSet;

/**
 * 定义栈的数据结构，
 * 请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * @author itning
 * @date 2019/7/8 13:24
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(6);
        solution.push(5);
        solution.push(4);
        solution.push(3);
        solution.push(2);
        solution.push(8);
        int min = solution.min();
        System.out.println(min);
    }

    Stack<Integer> stack = new Stack<>();

    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        TreeSet<Integer> set = new TreeSet<>(stack);
        return set.first();
    }
}
