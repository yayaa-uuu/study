package com.wx.clone;

public class Test {
    public static void main(String[] args) {
        Person person = new Person(new Address("武汉"));
        Person clone = person.clone();
        System.out.println(person.getAddress() == clone.getAddress());
    }
}
