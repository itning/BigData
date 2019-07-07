package top.itning.string;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 *
 * @author itning
 * @date 2019/7/7 17:30
 */
public class Solution {
    public static void main(String[] args) {
        String s = new Solution().replaceSpace(new StringBuffer("We Are Happy"));
        System.out.println(s);
    }

    public String replaceSpace(StringBuffer str) {
        int index;
        do {
            index = str.indexOf(" ");
            if (index != -1) {
                str.deleteCharAt(index);
                str.insert(index, "%20");
            }
        } while (index != -1);
        return str.toString();
    }
}
