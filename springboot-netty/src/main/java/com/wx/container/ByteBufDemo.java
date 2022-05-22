package com.wx.container;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * netty 中缓冲区使用
 */
public class ByteBufDemo {
    public void heapBufTest() {
        //堆缓冲区
        ByteBuf heapBuf = Unpooled.buffer();
        //检查 ByteBuf 是否有一个支撑数组
        if (heapBuf.hasArray()) {
            //如果有，则获取对该数组的引用
            byte[] array = heapBuf.array();
            //计算第一个字节的偏移量
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            //获得可读字节数组
            int length = heapBuf.readableBytes();
            //使用数组、偏移量和长度
            //作为参数调用你的方法
//            handleArray(array, offset, length);
        }
    }

    public void directBufTest() {
        //直接缓冲区
        ByteBuf directBuf = Unpooled.directBuffer();
        //检查 ByteBuf 是否由数组支撑。如果不是，则这是一个直接缓冲区
        if (!directBuf.hasArray()) {
            //获取可读字节数
            int length = directBuf.readableBytes();
            //分配一个新的数组来保存具有该长度的字节数据
            byte[] array = new byte[length];
            //将字节复制到该数组
            directBuf.getBytes(directBuf.readerIndex(), array);
//            handleArray(array, 0, length);
        }
    }

    public void compositeByteBufTest() {
        //复合缓冲区
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
//        ByteBuf headerBuf = ...; // can be backing or direct
//        ByteBuf bodyBuf = ...; // can be backing or direct
        //将 ByteBuf 实例追加到 CompositeByteBuf
//        messageBuf.addComponents(headerBuf, bodyBuf);
        //删除位于索引位置为 0（第一个组件）的 ByteBuf
        messageBuf.removeComponent(0); // remove the header
        //循环遍历所有的 ByteBuf 实例
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
    }
    public void compositeByteBufTest2() {
        CompositeByteBuf compBuf = Unpooled.compositeBuffer();
        int length = compBuf.readableBytes();
        byte[] array = new byte[length];
        compBuf.getBytes(compBuf.readerIndex(), array);
//        handleArray(array, 0, array.length);
    }



}
