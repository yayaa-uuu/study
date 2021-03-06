package com.wx.rabbitmq.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 交换机类型 fanout,   bindingkey  所有绑定的队列都会收到生产者发送到队列的消息
 * direct             bindingkey  准确匹配
 * topic              bindingkey  可使用通配符
 * headers              少用
 * <p>
 * 死信队列，可看作延迟队列。
 */
public class DLXExchangeTest {

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
        channel.exchangeDeclare("exchange.dlx", "direct", true);

        channel.exchangeDeclare("exchange.normal", "fanout", true);
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000);
        map.put("x-dead-letter-exchange", "exchange.dlx");
        map.put("x-dead-letter-routing-key", "routingkey");
        channel.queueDeclare("queue.normal", true, false, false, map);
        channel.queueBind("queue.normal", "exchange.normal", "");
        channel.queueDeclare("queue.dlx", true, false, false, null);
        channel.queueBind("queue.dlx", "exchange.dlx", "routingkey");
        channel.basicPublish("exchange.normal", "rk",
                MessageProperties.PERSISTENT_TEXT_PLAIN, "dlx ".getBytes());

    }
}
