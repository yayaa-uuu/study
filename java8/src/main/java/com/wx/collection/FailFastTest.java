package com.wx.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FailFastTest {

    public static void main(String[] args) {
        Object lock = new Object();
//        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        List<String> list = new ArrayList<String>();
//        ArrayList<String> list = new ArrayList<>();
        list.add("123");
//        synchronized (list) {
        for (int i = 0; i < 10; i++) {
            list.add(list.get(i));
            System.out.println(list);
        }
//        for (String s : list) {   //iterator
//            list.add(s);
//            System.out.println(s);
//        }
//        }
//        for (int i = 0; i < 10; i++) {
//            list.add("s");
//            System.out.println(list.get(i));
//        }
    }

}
