package com.wx.hash;

public class HashCodeTest {
    public static void main(String[] args) {
        Study study = new Study();
        Study study2 = new Study();

        System.out.println(study.equals(study2));
    }
}

class Study {
    String id;
    String name;
}
