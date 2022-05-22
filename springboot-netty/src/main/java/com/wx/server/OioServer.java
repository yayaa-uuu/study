package com.wx.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class OioServer {
    public void server(int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        try {
            for (; ; ) {
                Socket clientSocket = socket.accept();
                System.out.println("accept connection from " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("Hi\r\n".getBytes(StandardCharsets.UTF_8));
                            out.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                //ignore  on clone
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
