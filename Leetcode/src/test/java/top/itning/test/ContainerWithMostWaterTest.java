package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 *
 * @author itning
 * @since 2021/12/6 20:57
 */
public class ContainerWithMostWaterTest {
    @Test
    void containerWithMostWaterTest() {
        Assertions.assertEquals(49, maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        Assertions.assertEquals(49, maxArea(new int[]{7, 3, 8, 4, 5, 2, 6, 8, 1}));
        Assertions.assertEquals(1, maxArea(new int[]{2, 1}));
    }

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 2; j <= height.length; j++) {
                if (height[i] <= height[j - 1]) {
                    max = Math.max(max, height[i] * (j - i - 1));
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (height[i] <= height[j]) {
                    max = Math.max(max, height[i] * (i - j));
                }
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }
}
