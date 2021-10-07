package com.wx.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxli
 * @date 2021/9/2 20:45
 */
public class CollectionTest {
    private static Map<String, String> map = new ConcurrentHashMap<>();

    static {
        map.put("1", "2");
    }

    public static void main(String[] args) {
        String s = map.putIfAbsent("1", "3");
        if (s == null) {

        }else {
            System.out.println(s);
            throw new RuntimeException("his");
        }
        System.out.println(map.get("1"));

        String s1 = map.putIfAbsent("2", "3");
        System.out.println(s1);
        if (s1 == null) {
            System.out.println(map.get("2"));
        }
    }
}
