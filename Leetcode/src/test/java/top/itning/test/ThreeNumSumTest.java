package top.itning.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author itning
 * @since 2021/12/7 19:09
 */
public class ThreeNumSumTest {
    @Test
    void threeNumSumTest() {
        System.out.println(threeSum(new int[]{0, 3, 0, 1, 1, -1, -5, -5, 3, -3, -3, 0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return lists;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int curr = nums[i];
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while (L < R && nums[L + 1] == nums[L]) {
                        ++L;
                    }
                    while (L < R && nums[R - 1] == nums[R]) {
                        --R;
                    }
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }
}
