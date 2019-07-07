package top.itning.find;

import java.util.Arrays;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * ???
 *
 * @author itning
 * @date 2019/7/7 19:25
 */
public class Solution {
    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 2};
        int i = new Solution().minNumberInRotateArray(array);
        System.out.println(i);
    }

    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
//        int min = array[0];
//        int minIndex = 0;
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] < min) {
//                minIndex = i;
//                min = array[i];
//            }
//        }
//        if(minIndex)
        Arrays.sort(array);
        return array[0];
    }
}
