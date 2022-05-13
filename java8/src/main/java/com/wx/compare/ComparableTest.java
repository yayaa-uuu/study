package com.wx.compare;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ComparableTest {
    public static void main(String[] args) {
        System.out.println(Integer.compare(1, 1));
        System.out.println(Integer.compare(1, 2));
        List<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(1);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
