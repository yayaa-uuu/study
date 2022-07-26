package com.wx.homework;

import java.util.Arrays;

/**
 * 二维数组 行列交换
 */
public class T1_1_13 {

    public static void main(String[] args) {
        int M = 2;
        int N = 3;
        int init = 0;
        int[][] a = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = ++init;
            }
        }
        int[][] b = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                b[i][j] = a[j][i];
            }
        }
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));
    }

}
