package com.wx.binary;

/**
 * 复制数组
 */
public class Clone {
    public int[] clone(int[] nums) {
        int[] b = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            b[i] = nums[i];
        }
        return b;
    }
}
