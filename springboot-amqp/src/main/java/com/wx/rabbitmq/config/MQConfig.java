package com.wx.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ErrorHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeParseException;

@Slf4j
@Configuration
public class MQConfig {

    private final int prefetchCount=30;
    private final String url = "amqp://admin:123456@119.91.96.216:5672";

    @Bean("rabbitAdmin")
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return createConnectionFactory(url);
    }

    private ConnectionFactory createConnectionFactory(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("cloud mq uri error.", e);
        }
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(uri);
        // 10s 连接超时
        connectionFactory.setConnectionTimeout(10_000);
        // 10s 心跳
        connectionFactory.setRequestedHeartBeat(30);
//        StandardMetricsCollector metricsCollector = new StandardMetricsCollector(metricRegistry);
//        connectionFactory.getRabbitConnectionFactory().setMetricsCollector(metricsCollector);

        return connectionFactory;
    }

    /**
     * 构造一个 RabbitListener 构造工厂，注入自定义异常处理器由 {@code errorHandler()} 生成
     *
     * @param errorHandler      自定义错误处理器
     * @param connectionFactory RabbitMQ 连接工厂
     * @param consumerCount     @RabbitListener 构造最少 worker 数
     * @param maxConsumerCount  @RabbitListener 构造最多 worker 数
     * @return 自定义错误处理的 RabbitListener
     */
    @Bean("listenerContainerFactory")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(ErrorHandler errorHandler,
                                                                              @Qualifier("connectionFactory") ConnectionFactory connectionFactory,
                                                                              @Value("${tnar.MQConsumers:1}") int consumerCount,
                                                                              @Value("${tnar.MQMaxConsumers:1}") int maxConsumerCount,
                                                                              @Qualifier("mqTaskExecutor") TaskExecutor taskExecutor) {
        return createListenerContainerFactory(errorHandler, connectionFactory, consumerCount, maxConsumerCount, taskExecutor, prefetchCount);
    }

    private SimpleRabbitListenerContainerFactory createListenerContainerFactory(ErrorHandler errorHandler,
                                                                                ConnectionFactory connectionFactory,
                                                                                int consumerCount,
                                                                                int maxConsumerCount,
                                                                                TaskExecutor taskExecutor,
                                                                                int prefetchCount) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setErrorHandler(errorHandler);
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setConcurrentConsumers(consumerCount);
        containerFactory.setMaxConcurrentConsumers(maxConsumerCount);
        containerFactory.setIdleEventInterval(60_000L);
        containerFactory.setPrefetchCount(prefetchCount);
        containerFactory.setTaskExecutor(taskExecutor);
        /*containerFactory.setChannelTransacted(true);
        containerFactory.setTransactionManager(transactionManager);*/
        return containerFactory;
    }


    @Bean
    public Binding testBinding(@Value("${queue:test}") String queueName,
                           @Value("${key:hello.#}") String routingKey,
                           @Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        return createBinding(queueName, "amq.topic", routingKey, rabbitAdmin);
    }

    private Binding createBinding(String queueName, String exchangeName, String routingKey, RabbitAdmin rabbitAdmin) {
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
        binding.setAdminsThatShouldDeclare(rabbitAdmin);
        return binding;
    }

    @Bean
    public Queue cloudPaymentQueue(@Value("test") String queueName,
                                   @Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        return createServerQueue(queueName, rabbitAdmin);
    }

    private Queue createClientQueue(String queueName, RabbitAdmin rabbitAdmin) {
        return createQueue(queueName, rabbitAdmin, false);
    }

    private Queue createServerQueue(String queueName, RabbitAdmin rabbitAdmin) {
        log.info("create server queue {}", queueName);
        return createQueue(queueName, rabbitAdmin, false);
    }

    private Queue createQueue(String queueName, RabbitAdmin rabbitAdmin, boolean autoDelete) {
        Queue queue = new Queue(queueName, true, false, autoDelete);
        queue.setAdminsThatShouldDeclare(rabbitAdmin);
        return queue;
    }

    /**
     * 定义一个 异常处理用于注入 RabbitListener 的构造工厂的错误处理器
     *
     * @return 错误处理器使用自定义策略 {@link FlyExceptionStrategy}
     */
    @Bean
    public ConditionalRejectingErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new FlyExceptionStrategy());
    }

    /**
     * RabbitMQ 自定义异常策略，增加用户认为的致命错误的类型，添加的类型将会被丢弃而不会重新入队
     */
    static class FlyExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
        @Override
        protected boolean isUserCauseFatal(Throwable cause) {
            return cause instanceof NullPointerException
                    || cause instanceof IllegalStateException
                    || cause instanceof DateTimeParseException
                    || cause instanceof IllegalArgumentException;
        }
    }

    @Bean("mqTaskExecutor")
    public TaskExecutor mqExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        final int poolSize = 20;
        taskExecutor.setCorePoolSize(poolSize);
        taskExecutor.setMaxPoolSize(poolSize);
        taskExecutor.setThreadNamePrefix("MQ-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
