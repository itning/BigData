package top.itning.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 1 2 3 4 5
 * 2 3 4 5 6
 * 3 4 5 6 7
 * 4 5 6 7 8
 * 5 6 7 8 9
 *
 * @author itning
 * @date 2019/7/7 16:32
 */
public class Solution {
    public static void main(String[] args) {
        int[][] array = {
                {},
                {2, 8, 13, 39, 100},
                {3, 10, 16, 44, 101}
        };
        boolean find = new Solution().Find(8, array);
        System.out.println(find);
    }

    public boolean Find(int target, int[][] array) {
        int oneLength = array[0].length;
        int twoLength = array.length;
        if (oneLength == 0 ) {
            return false;
        }
        int min = array[0][0];
        int max = array[twoLength - 1][oneLength - 1];
        if (target == max || target == min) {
            return true;
        } else {
            if (max < target) {
                //大于最大值
                return false;
            } else if (min > target) {
                //小于最小值
                return false;
            } else {
                HashSet<Integer> set = new HashSet<>(oneLength * twoLength);
                Arrays.stream(array).flatMapToInt(Arrays::stream).forEach(set::add);
                return set.contains(target);
            }
        }
    }
}
