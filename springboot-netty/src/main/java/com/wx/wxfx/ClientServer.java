package com.wx.wxfx;

import com.wx.wxfx.client.Disposable;
import com.wx.wxfx.entity.Space;
import com.wx.wxfx.entity.Transmission;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.wx.wxfx.entity.Transmission.MessageType.TYPE_SPACE;

public class ClientServer implements Disposable {

    private final EventLoopGroup scheduler;
    private final Bootstrap clientBootstrap;

    private int reconnectDelayMs;
    private volatile Channel activeChannel;
    private AtomicBoolean shouldReconnect;

    public ClientServer(EventLoopGroup scheduler, Bootstrap clientBootstrap) {
        this.scheduler = scheduler;
        this.clientBootstrap = clientBootstrap;
        this.reconnectDelayMs = 1;
        this.shouldReconnect = new AtomicBoolean(true);
    }

    public Disposable start() {
        //Create a new connectFuture
        ChannelFuture connectFuture = clientBootstrap.connect();

        connectFuture.addListeners((ChannelFuture cf) -> {
            if (cf.isSuccess()) {
                System.out.println("Connection established");
                reconnectDelayMs = 1;
                activeChannel = cf.channel();

                //Listen to the channel closing
                ChannelFuture closeFuture = activeChannel.closeFuture();
                closeFuture.addListeners((ChannelFuture closeFut) -> {
                    if (shouldReconnect.get()) {
                        activeChannel.eventLoop().schedule(this::start, nextReconnectDelay(), TimeUnit.MILLISECONDS);
                    } else {
                        System.out.println("Session has been disposed won't reconnect");
                    }
                });
            } else {
                int delay = nextReconnectDelay();
                System.out.println("Connection failed will re-attempt in " + delay + " ms");
                cf.channel().eventLoop().schedule(this::start, delay, TimeUnit.MILLISECONDS);
            }
        });

        return this;
    }

    /**
     * Call this to end the session
     */
    public void dispose() {
        try {
            shouldReconnect.set(false);
            scheduler.shutdownGracefully().sync();
            if (activeChannel != null) {
                activeChannel.closeFuture().sync();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while shutting down TcpClient");
        }
    }

    private int nextReconnectDelay() {
        this.reconnectDelayMs = this.reconnectDelayMs * 2;
        return Math.min(this.reconnectDelayMs, 5000);
    }

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup(1);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress("localhost", 9000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 512 << 10, 0, 4, 0, 4, true))
                                // 解码 protobuf
                                .addLast(new ProtobufDecoder(Transmission.getDefaultInstance()))
                                .addLast(new IdleStateHandler(0, 20, 0, TimeUnit.SECONDS))
                                // 字节前 4 位小端写入编码长度
                                .addLast(new SimpleChannelInboundHandler<Transmission>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, Transmission msg) throws Exception {
                                        System.out.println(msg);
                                    }

                                    @Override
                                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                        if(evt instanceof IdleStateEvent) {
                                            IdleStateEvent event = (IdleStateEvent) evt;
                                            if(IdleState.WRITER_IDLE.equals(event.state())) {
                                                System.out.println("ping");
                                                Space space = Space.newBuilder()
                                                        .setRemained(123)
                                                        .setTotal(200)
                                                        .build();
                                                Transmission transmission = Transmission.newBuilder()
                                                        .setID(7755)
                                                        .setType(TYPE_SPACE)
                                                        .setMessage(space.toByteString()).build();
                                                ctx.channel().writeAndFlush(transmission).addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
                                            }
                                        }
                                        super.userEventTriggered(ctx, evt);
                                    }
                                })
                                .addLast(new LengthFieldPrepender(ByteOrder.LITTLE_ENDIAN, 4, 0, false))
                                // 编码 protobuf
                                .addLast(new ProtobufEncoder());
                    }
                });
        ClientServer clientServer = new ClientServer(group, bootstrap);
        clientServer.start();
    }
}
