package top.itning.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。
 * 队列中的元素为int类型。
 * 4
 * 8
 * 6
 * 3
 * 1
 *
 * @author itning
 * @date 2019/7/7 19:01
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(1);
        solution.push(3);
        solution.push(6);
        solution.push(8);
        solution.push(4);
        System.out.println(solution.pop());
        System.out.println(solution.pop());
    }

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        int re = stack2.pop();

        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        return re;
    }
}
