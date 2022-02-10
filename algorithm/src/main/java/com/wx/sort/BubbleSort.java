package com.wx.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    static int[] a = new int[]{1, 4, 3, 5, 2, -1};

    public static void main(String[] args) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    //交换位置
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));


    }


}
