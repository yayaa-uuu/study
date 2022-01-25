package com.wx.rabbitmq.demo.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ControllerV2 {
    private String key = "hello";
    private String exchange = "amq.topic";

    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;

    public ControllerV2(AmqpTemplate amqpTemplate,
                        RabbitTemplate rabbitTemplate){
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping("/hello")
    public void send() {
        log.info("发送消息");
        rabbitTemplate.convertAndSend(exchange, key + "." + "1", "hello");
        rabbitTemplate.convertAndSend(exchange, key + "." + "2", "hello");
        rabbitTemplate.convertAndSend(exchange, key + "." + "3", "hello");
    }

    @RabbitListener(containerFactory = "listenerContainerFactory",
            queues = "test",
            admin = "rabbitAdmin")
    public void consumer(String json) {
        log.info(json);
    }
}
