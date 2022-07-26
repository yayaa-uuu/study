package com.wx.homework;

import org.junit.Test;

public class T1_1_7 {
    @Test
    public void a() {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > 0.01)
            t = (9.0 / t + t) / 2.0;
        System.out.printf("%.5f\n", t);
    }

    @Test
    public void b() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        System.out.println(sum);
    }

    @Test
    public void c() {
        int sum = 0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        System.out.println(sum);
    }

}
