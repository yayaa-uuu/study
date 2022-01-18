package cn.tnar.msf.cm4.msg.dto.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {
    private String key = "key";
    private String exchange = "amq.topic";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/hello")
    public void send() {
        amqpTemplate.convertAndSend(exchange, key + "." + "1", "hello");
        amqpTemplate.convertAndSend(exchange, key + "." + "2", "hello");
        amqpTemplate.convertAndSend(exchange, key + "." + "3", "hello");
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("test"),
            exchange = @Exchange(value = "amq.topic"),
            key = {"key.*"}
    ))
    public void consumer(String json) {
        log.info(json);
    }
}
