package com.wx.homework;

import java.util.Scanner;

public class T1_1_9 {
    /**
     * 将一个正整数N 用二进制表示
     * Integer.toBinaryString(N)
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "";
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int n = N; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        System.out.println(s);
    }

}
