package com.wx.array;

import java.util.Arrays;

/**
 * 数组常见方法
 */
public class Commons {
    /**
     * 找出数组最大的元素
     * @param nums
     * @return
     */
    public int max(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    /**
     * 计算数组平均值
     * 求和，除以数组长度
     *
     * @param nums
     * @return
     */
    public int avg(int[] nums) {
        int asInt = Arrays.stream(nums).reduce(Integer::sum).getAsInt();
        return asInt / (nums.length - 1);
    }

    /**
     * 复制数组
     *
     * @param nums
     * @return
     */
    public int[] clone(int[] nums) {
        int[] b = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            b[i] = nums[i];
        }
        return b;
    }

    /**
     * 颠倒数组顺序
     *
     * @param nums
     * @return
     */
    public int[] reverse(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[N - 1 - i];
            nums[N - 1 - i] = temp;
        }
        return nums;
    }
}
