package com.wx.finaltest;

import java.util.Arrays;

/**
 * @author wxli
 * @date 2021/7/25 22:35
 */
public class Vector {
    private final double[] coord;

    public Vector(double[] coord) {
        this.coord = Arrays.copyOf(coord, coord.length);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "coord=" + Arrays.toString(coord) +
                '}';
    }

    public static void main(String[] args) {
        double[] a = {3.0, 4.0};
        Vector vector = new Vector(a);
        a[0] = 0.0;
        System.out.println(vector.toString());
    }
}
