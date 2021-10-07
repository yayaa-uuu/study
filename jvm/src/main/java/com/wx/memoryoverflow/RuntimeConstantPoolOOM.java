package com.wx.memoryoverflow;

/**
 * @author wxli
 * @date 2021/9/13 11:19
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String intern = str1.intern();
        System.out.println(str1);
        System.out.println(intern);

        System.out.println(intern == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
