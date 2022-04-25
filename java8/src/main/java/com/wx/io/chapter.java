package com.wx.io;

import java.nio.ByteOrder;

/**
 * 大小端
 */
public class chapter {
    public static void main(String[] args) {
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            System.out.println("BIG_ENDIAN");
        } else {
            System.out.println("LITTLE_ENDIAN");
        }
    }
}
