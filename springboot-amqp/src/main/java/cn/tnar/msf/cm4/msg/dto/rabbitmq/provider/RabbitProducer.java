package cn.tnar.msf.cm4.msg.dto.rabbitmq.provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者首先和rabbit服务器建立一个连接（connection），
 * 然后在这个连接之上创建一个channel
 * 之后创建一个交换机Exchange和一个队列Queue，
 * 并通过路由键（ROUTING_KEY）进行绑定。
 *
 * @author wxli
 * @date 2021/8/7 23:35
 */
public class RabbitProducer {
    private static final String EXCHANGE_NAME = " exchange demo ";
    private static final String ROUTING_KEY = " routingkey demo ";
    private static final String QUEUE_NAME = "queue demo ";
    private static final String IP_ADDRESS = "192.168.0.2";
    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为 5672

    public static void main(String[] args) throws IOException,
            TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(" root ");
        factory.setPassword(" root123 ");
        Connection connection = factory.newConnection();// 创建连接
        Channel channel = connection.createChannel();// 创建信道
        // 创建一个 type="direct" 、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, " direct ", true, false, null);
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        //发送一条持久化的消息: hello world !
        String message = "Hello World !";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes());
        //关闭资源
        channel.close();
    }
}
