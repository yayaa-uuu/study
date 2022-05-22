package com.wx.initializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 处理由行尾符分隔的帧
 *
 * ABC\r\nDEF\r\n
 * 第一个帧为ABC
 * 第二个帧为DEF
 */
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(64 * 1024));
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // Do something with the data extracted from the frame
        }
    }
}