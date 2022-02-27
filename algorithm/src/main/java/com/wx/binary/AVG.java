package com.wx.binary;

import java.util.Arrays;

/**
 * 计算数组平均值
 * 求和，除以数组长度
 */
public class AVG {
    public int avg(int[] nums) {
        int asInt = Arrays.stream(nums).reduce(Integer::sum).getAsInt();
        return asInt / (nums.length - 1);
    }
}
