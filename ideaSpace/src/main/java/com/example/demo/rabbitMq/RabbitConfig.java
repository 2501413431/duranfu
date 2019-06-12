//package com.example.demo.rabbitMq;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Describe
// * @Auth duranfu
// * @Date 2019/4/26
// */
//@Configuration
//public class RabbitConfig {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//
//    @Value("${spring.rabbitmq.vhost}")
//    private String virtualHost;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//
//    @Value("${spring.rabbitmq.publisher-confirms}")
//    private boolean confirms;
//
//
//    public static final String EXCHANGE_A = "my-mq-exchange_A";
//    public static final String EXCHANGE_B = "my-mq-exchange_B";
//    public static final String EXCHANGE_C = "my-mq-exchange_C";
//
//
//    public static final String QUEUE_A = "QUEUE_A";
//    public static final String QUEUE_B = "QUEUE_B";
//    public static final String QUEUE_C = "QUEUE_C";
//
//    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
//    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
//    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";
//
//    @Bean(name = "mainConnectionFactory")
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost(virtualHost);
//        connectionFactory.setPublisherConfirms(confirms);
//        return connectionFactory;
//    }
//
//    @Bean(name = "mainRabbitTemplate")
////    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate(@Qualifier("mainConnectionFactory") ConnectionFactory mainConnectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(mainConnectionFactory);
//        template.setMessageConverter(messageConverter());
//        return template;
//    }
//
//    @Bean(name = "mainMessageConverter")
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean(name = "mainAmqpAdmin")
//    public AmqpAdmin amqpAdmin(@Qualifier("mainConnectionFactory") ConnectionFactory mainConnectionFactory) {
//        return new RabbitAdmin(mainConnectionFactory);
//    }
//
//
////    @Bean
////    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(@Qualifier("mainConnectionFactory") ConnectionFactory mainConnectionFactory){
////        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
////        factory.setConnectionFactory(mainConnectionFactory);
////        factory.setMessageConverter(new Jackson2JsonMessageConverter());
////        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);             //开启手动 ack
////        return factory;
////    }
//
//
//}
