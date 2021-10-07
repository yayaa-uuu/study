package com.wx.finaltest;

/**
 * @author wxli
 * @date 2021/7/25 22:27
 */
public class StringTest {
    //string  class 被 final 修饰，类不可变
    static String name = "hello";

    public static void test2(String a) {
        //实际上发生了赋值动作

        //打印a即可验证
        a = "nihao";
        //打印a即可验证
    }

    public static void main(String[] args) {
        test2(name);
        System.out.println(name);

        char[] values = new char[]{'n', 'i'};
        String lll = new String(values);
        //string 构造器中做了深拷贝。

        //改变values引用指向不会影响String对象
        values[0] = 'l';
        System.out.println(lll);
    }


}
