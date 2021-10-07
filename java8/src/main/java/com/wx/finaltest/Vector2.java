package com.wx.finaltest;

import java.util.Arrays;

/**
 * final非常不幸的只能用来保证原始数据类型的实例变量的不可变性，
 * 而无法用于引用类型的变量，如果一个引用类型的实例变量含有修饰符final，
 * 该实例变量的值（某个对象的引用）就永远无法改变了---它将永远指向同一个对象，
 * 但对象的值本身仍然是可变的。
 * 如下。
 * 为什么string是不可变的，根本原因，类被final修饰，
 * string 里面的   char[]  values 在构造器中是深克隆了一个数组，
 * 修改外面的values即不会影响克隆的对象。
 * 具体可见StringTest    和Vector
 *
 * @author wxli
 * @date 2021/7/25 22:37
 */
public final class Vector2 {
    private final double coord[];

    public Vector2(double[] coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "coord=" + Arrays.toString(coord) +
                '}';
    }

    public static void main(String[] args) {
        double[] a = {3.0, 4.0};
        Vector2 vector = new Vector2(a);
        a[0] = 0.0;
        System.out.println(vector.toString());
    }
}
