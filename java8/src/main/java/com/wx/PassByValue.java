package com.wx;

import java.util.Arrays;

/**
 * 值传递
 *
 * @author wxli
 * @date 2021/7/23 20:47
 */
public class PassByValue {
    static int a = 1;

    public static void test(int a) {
        a = 2;
    }

    int b = 1;

    public void test2(int b) {
        b = 2;
    }

    public static void main(String[] args) {
        test(3);
        System.out.println(a);

        PassByValue test = new PassByValue();
        test.test2(test.b);
        System.out.println(test.b);


        int[] ints = new int[]{1, 3, 2};
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));

        int[] c = ints;

        c[1] = 5;

        System.out.println(Arrays.toString(ints));
    }
}
