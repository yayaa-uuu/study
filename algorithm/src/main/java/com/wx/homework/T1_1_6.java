package com.wx.homework;

public class T1_1_6 {
    public static void main(String[] args) {
        int f = 9;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }
}
