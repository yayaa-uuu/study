package com.wx.exceptiontest;


/**
 * @author wxli
 * @date 2021/7/27 16:14
 */
public class ExceptionTest {
    public static void main(String[] args) {
        int a = 1;
        try {
            if (a > 0) {
                throw new RuntimeException("nihao");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
