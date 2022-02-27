package com.wx.binary;

/**
 * 颠倒数组顺序
 */
public class Reverse {
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
