package top.itning.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author itning
 * @since 2022/2/13 20:29
 */
public class RemoveElementTest {
    @Test
    void removeElementTest() {
        int[] ints = {0,1,2,2,3,0,4,2};
        int i = removeElement(ints, 2);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));
    }

    public int removeElement(int[] nums, int val) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[temp] = nums[i];
                temp++;
            }
        }
        return temp;
    }
}
