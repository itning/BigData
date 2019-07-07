package top.itning.integrity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @author itning
 * @date 2019/7/7 21:44
 */
public class Solution2 {
    public static void main(String[] args) {
        new Solution2().reOrderArray(new int[]{4, 67, 1, 6, 7});
    }

    public void reOrderArray(int[] array) {
        List<Integer> odd = new ArrayList<>(array.length);
        List<Integer> even = new ArrayList<>(array.length);
        for (int item : array) {
            if (item % 2 == 0) {
                even.add(item);
            } else {
                odd.add(item);
            }
        }
        Integer[] toArray = Stream.of(odd, even).flatMap(Collection::stream).toArray(Integer[]::new);
        for (int i = 0; i < array.length; i++) {
            array[i] = toArray[i];
        }
    }
}
