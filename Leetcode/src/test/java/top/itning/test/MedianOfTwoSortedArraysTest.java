package top.itning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * @author itning
 * @since 2021/12/6 11:07
 */
public class MedianOfTwoSortedArraysTest {

    @Test
    void findMedianSortedArraysTest() {
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3, 4}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{1, 4}, new int[]{2, 3}));
        Assertions.assertEquals(2, findMedianSortedArrays(new int[]{1, 2}, new int[]{3}));
        Assertions.assertEquals(3, findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5}));
        Assertions.assertEquals(3, findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4, 5}));
        Assertions.assertEquals(2, findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{}));
    }

    /**
     * 没做到时间复杂度 O(log (m+n)) 。
     * 这个时间复杂度应该是 O((m+n)/2)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int medianIndex = totalLength / 2;
        boolean isDual = totalLength % 2 == 0;
        int index1 = 0;
        int index2 = 0;
        // 1,2,3,4  1,2,3 √
        int last = 0;
        for (int i = 0; i <= medianIndex; i++) {
            int num1 = Integer.MAX_VALUE;
            int num2 = Integer.MAX_VALUE;
            if (index1 < nums1.length) {
                num1 = nums1[index1];
            }
            if (index2 < nums2.length) {
                num2 = nums2[index2];
            }
            int num;
            if (num1 <= num2) {
                num = num1;
                index1++;
            } else {
                num = num2;
                index2++;
            }
            if (isDual) {
                if (i == medianIndex - 1) {
                    last = num;
                }
                if (i == medianIndex) {
                    return (double) (last + num) / 2;
                }
            } else {
                if (i == medianIndex) {
                    return num;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
