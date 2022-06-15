package com.wx.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/fizz-buzz/
 */
public class T412 {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else {
                list.add(i + "");
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(15 % 3);
        System.out.println(15 % 5);
    }
}
