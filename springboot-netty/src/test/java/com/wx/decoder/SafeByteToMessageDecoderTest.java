package com.wx.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

public class SafeByteToMessageDecoderTest{
    @Test
    public void testDecode() {
        ByteBuf byteBuf= Unpooled.buffer();
        byteBuf.capacity(2048);
        byte[] array = byteBuf.array();
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) i;
        }
        EmbeddedChannel channel = new EmbeddedChannel(new SafeByteToMessageDecoder());
        channel.writeInbound(byteBuf);
        channel.finish();

    }
}