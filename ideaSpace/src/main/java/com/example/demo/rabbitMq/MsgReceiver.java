//package com.example.demo.rabbitMq;
//
//import com.rabbitmq.client.AMQP;
////import com.rabbitmq.client.Channel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Describe
// * @Auth duranfu
// * @Date 2019/4/26
// */
////@Component
////@RabbitListener(queues = RabbitConfig.QUEUE_A)
//public class MsgReceiver {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
////    @RabbitHandler
//    @RabbitListener(queues = RabbitConfig.QUEUE_A)
////    public void process(String content, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
//    public void process(String content) {
//        logger.info("接收处理队列A当中的消息： " + content);
////        try {
////            channel.basicAck(tag,true);            // 确认消息
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//}
//
