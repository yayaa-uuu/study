package com.wx.statictest;

/**
 * @author wxli
 * @date 2021/9/6 13:59
 */
public class Test {
    static Integer a = 0;


    static {
        a = 1;
        b = 1;
    }
    static Integer b = 0;


    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
    }
}
