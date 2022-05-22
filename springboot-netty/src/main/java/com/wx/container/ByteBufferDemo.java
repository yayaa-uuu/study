package com.wx.container;

import java.nio.ByteBuffer;

public class ByteBufferDemo {

    //header头部可能出现重用

    //jdk混合缓冲区
    public void fun(ByteBuffer header, ByteBuffer body) {
        ByteBuffer[] message = new ByteBuffer[]{header, body};
        ByteBuffer message2 = ByteBuffer.allocate(header.remaining() + body.remaining());
        message2.put(header);
        message2.put(body);
        message2.flip();
    }

}
