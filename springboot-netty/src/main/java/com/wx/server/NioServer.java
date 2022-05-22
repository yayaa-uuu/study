package com.wx.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public void server(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket socket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);
        Selector selector = Selector.open();    //打开Selector来处理channel
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);   //将ServerChannel注册到Selector以接受连接
        final ByteBuffer msg = ByteBuffer.wrap("Hi\t\n".getBytes(StandardCharsets.UTF_8));
        for (; ; ) {
            try {
                selector.select();    //等待需要处理的新事件；阻塞将一直持续到下一个传入事件
            } catch (IOException e) {
                e.printStackTrace();
                //handler exception
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();    //获取所有接受事件的SelectionKey实例
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //接受客户端连接并将它注册到选择器
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println(
                                "Accepted connection from " + client);
                    }
                    //检查套接字是否准备好数据
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {   //将数据写入已连接的客户端
                                break;
                            }
                        }
                        client.close();     //关闭连接
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                        // ignore on close
                    }
                }
            }
        }
    }


}
