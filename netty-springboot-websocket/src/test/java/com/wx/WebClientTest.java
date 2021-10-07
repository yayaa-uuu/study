package com.wx;

import cn.tnar.msf.cm4.msg.dto.Transmission;
import com.google.protobuf.InvalidProtocolBufferException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author wxli
 * @date 2021/8/30 11:21
 */
public class WebClientTest {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://127.0.0.1:80/ws/xxx?req=ok")) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {
                message.getBytes();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
        webSocketClient.connect();
        Thread.sleep(10000);

        Transmission transmission = Transmission.newBuilder()
                .setID(1)
                .build();
        webSocketClient.send(transmission.toByteArray());

        Thread.sleep(10000);
    }
}
