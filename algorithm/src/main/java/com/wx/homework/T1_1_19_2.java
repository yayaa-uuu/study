package com.wx.homework;

public class T1_1_19_2 {
    public static long[] F(long[] a) {
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i < 100; i++) {
            a[i] = a[i - 2] + a[i - 1];
            System.out.println(i + " " + a[i]);
        }
        return a;
    }

    public static void main(String[] args) {
        long[] a = new long[100];
        a = F(a);
    }

}
