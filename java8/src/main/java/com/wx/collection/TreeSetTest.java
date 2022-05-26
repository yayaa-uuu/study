package com.wx.collection;

import java.util.TreeSet;

public class TreeSetTest {
    /**
     * treeSet add()
     * T需要实现Comparable 接口
     * @param args
     */
    public static void main(String[] args) {
        TreeSet<Study> studies = new TreeSet<>();
        studies.add(new Study());
    }
}

class Study {
    String id;
    String name;
}