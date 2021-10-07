package com.wx.binary;

import org.junit.Test;

import java.util.Arrays;

/**
 * 二分查找    0,1,2,3,4
 * 记录索引位置  lo=0   hi=4
 * 公式    mid=lo+（hi-lo）/2
 * 输入需要查找的    key
 * 比较    if（key>a[mid]）   lo=mid +1
 * else if (key<a[mid])    hi=mid-1
 * else return      mid;
 *
 *
 * @author wxli
 * @date 2021/7/24 17:53
 */
public class BinarySort {
    @Test
    public void test1() {
        int[] a = new int[]{0, 1, 2, 3, 4};
        int value = find(a, 5);
        System.out.println(value);
    }

    @Test
    public void test2() {
        int[] a = new int[]{0, 1, 2, 3, 4};
        Arrays.sort(a);
        int key = 3;
        int lo = 0;
        int hi = a.length - 1;
        int value = find2(a, key, lo, hi);
        System.out.println(value);
    }


    public static int find(int[] a, int key) {
        //数组得是顺序排列
        Arrays.sort(a);
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 编写递归，
     * 第一个条件应该为终止条件
     *
     */
    public static int find2(int[] a, int key, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (lo > hi) return -1;
        else if (key < a[mid]) return find2(a, key, lo, mid - 1);
        else if (key > a[mid]) return find2(a, key, mid + 1, hi);
        else return mid;
    }

}
