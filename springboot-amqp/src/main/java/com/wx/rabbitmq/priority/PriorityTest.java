package com.wx.rabbitmq.priority;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 死信队列，可看作延迟队列。
 */
public class PriorityTest {

    private static final String IP_ADDRESS = "119.91.96.216";
    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为 5672

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();// 创建连接
        Channel channel = connection.createChannel();// 创建信道
        channel.exchangeDeclare("exchange.priority", "direct", true);
        Map<String, Object> map = new HashMap<>();
        map.put("x-rnax-priority", 10);
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.priority(5);
        AMQP.BasicProperties properties = builder.build();
        channel.basicPublish(" exchange_priority", " rk_priority", properties, ("messages ").getBytes());

    }
}
