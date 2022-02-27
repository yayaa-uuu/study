package com.wx.search;


/**
 * 二分搜索
 * 标记左，右，中间位置。
 * 拿key和中间位置比较。
 */
public class BinarySearch {
    public int binarySearch(int[] nums, int key) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (lo + hi) / 2;
            if (nums[mid] > key) hi = mid - 1;
            else if (nums[mid] < key) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
