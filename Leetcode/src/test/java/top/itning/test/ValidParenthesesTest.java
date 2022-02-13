package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author itning
 * @since 2022/2/13 16:11
 */
public class ValidParenthesesTest {
    @Test
    void validParenthesesTest() {
        Assertions.assertTrue(isValid("()"));
        Assertions.assertTrue(isValid("()[]{}"));
        Assertions.assertFalse(isValid("(]"));
        Assertions.assertFalse(isValid("([)]"));
        Assertions.assertTrue(isValid("{[]}"));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> temp = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!temp.isEmpty()) {
                switch (c) {
                    case '(':
                    case '[':
                    case '{':
                        temp.push(c);
                        break;
                    case ')':
                        Character pop = temp.pop();
                        if (!pop.equals('(')) {
                            return false;
                        }
                        break;
                    case ']':
                        Character pop2 = temp.pop();
                        if (!pop2.equals('[')) {
                            return false;
                        }
                        break;
                    case '}':
                        Character pop3 = temp.pop();
                        if (!pop3.equals('{')) {
                            return false;
                        }
                        break;
                }
            } else {
                temp.push(c);
            }
        }
        return temp.isEmpty();
    }
}
