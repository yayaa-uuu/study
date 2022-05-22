package com.wx.wxfx;

import com.wx.wxfx.entity.Transmission;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.nio.ByteOrder;

public class CloudServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ChannelFuture f = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 512 << 10, 0, 4, 0, 4, true))
                                    // 解码 protobuf
                                    .addLast(new ProtobufDecoder(Transmission.getDefaultInstance()))
                                    // 字节前 4 位小端写入编码长度
                                    .addLast(new SimpleChannelInboundHandler<Transmission>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, Transmission msg) throws Exception {
                                            System.out.println(msg);
                                        }
                                    })
                                    .addLast(new LengthFieldPrepender(ByteOrder.LITTLE_ENDIAN, 4, 0, false))
                                    // 编码 protobuf
                                    .addLast(new ProtobufEncoder());

                        }
                    });
            f = bootstrap.bind(9000).syncUninterruptibly();
        } finally {
            if (f != null && f.isSuccess()) {
                System.out.println("tcp api server start success.");
            } else {
                System.out.println("tcp api server start failed.");
            }
        }
    }

}
