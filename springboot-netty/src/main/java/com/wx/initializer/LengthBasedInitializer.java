package com.wx.initializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 基于长度的协议
 * 解码前面的长度字段，然后将剩余的数据解码为ByteBuf
 * 解码前(14字节)                                                  解码后(12字节)
 * 长度                   实际内容                                    实际内容
 * 0X000C                “HELLO WORLD”                          “HELLO WORLD”
 * 被编码进了帧的前两个字节
 */
public class LengthBasedInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(64 * 1024, 0, 8));
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // Do something with the frame
        }
    }
}