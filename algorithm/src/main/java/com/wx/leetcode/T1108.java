package com.wx.leetcode;

public class T1108 {
    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "\\[\\.\\]");
    }
}
