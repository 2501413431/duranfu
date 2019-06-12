//package com.example.demo.rabbitMq;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.UUID;
//
///**
// * @Describe
// * @Auth duranfu
// * @Date 2019/4/26
// */
////@Component
//public class MsgProducer implements RabbitTemplate.ConfirmCallback {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
//    @Autowired
//    @Qualifier("mainRabbitTemplate")
//    private RabbitTemplate mainRabbitTemplate;
//
//    @Resource
//    private AmqpAdmin mainAmqpAdmin;
//
//    @PostConstruct
//    public void initRabbitMqInfo() {
//        DirectExchange exchange = new DirectExchange(RabbitConfig.EXCHANGE_A);
//        Queue statusQueue = new Queue(RabbitConfig.QUEUE_A);
//        mainAmqpAdmin.declareExchange(exchange);
//        mainAmqpAdmin.declareQueue(statusQueue);
//        mainAmqpAdmin.declareBinding(BindingBuilder.bind(statusQueue).to(exchange).with(RabbitConfig.QUEUE_A));
//    }
//
//    public void sendMsg(String content) {
//        mainRabbitTemplate.setConfirmCallback(this);
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        mainRabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
//    }
//    /**
//     * 回调
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        logger.info(" 回调id:" + correlationData);
//        if (ack) {
//            logger.info("消息成功发送");
//        } else {
//            logger.info("消息发送失败:" + cause);
//        }
//    }
//}
//
