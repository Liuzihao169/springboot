package com.kuake.srpingboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzihao
 * @create 2020-10-08-22:13
 *
 */
@Configuration
@Slf4j
public class RabbitMqConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("my.amqpAdmin.queue");
    }
    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange("my.direct");
    }
    @Bean
    public  Binding binding() {
        return  BindingBuilder.bind(testQueue()).to(myExchange()).with("my.key");
    }

    /**
     * 死信队列、死信交换机
     * @return
     */
    @Bean
    public Queue testQueueDead() {
        return new Queue("my.order.dead.queue");
    }
    @Bean
    public DirectExchange myExchangeDead() {
        return new DirectExchange("my.order.dead.exchange");
    }
    @Bean
    public  Binding bindingDead() {
        return  BindingBuilder.bind(testQueueDead()).to(myExchangeDead()).with("my.key");
    }


    @Bean
    public Queue testQueueOrder() {
        Map<String, Object> args = new HashMap<>();
        /**
         * x-dead-letter-exchange , value = '死信交换机的名字'
         * x-dead-letter-routing-key value='路由key'
         */
        args.put("x-dead-letter-exchange","my.order.dead.exchange");
        args.put("x-dead-letter-routing-key","my.key");
        //args.put("x-message-ttl",60000); 设置整个队列的过期时间
        return new Queue("my.amqpAdmin.queue.order",true,false,false, args);
    }
    @Bean
    public DirectExchange myExchangeOrder() {
        return new DirectExchange("my.direct.order");
    }
    @Bean
    public  Binding bindingOrder() {
        return  BindingBuilder.bind(testQueueOrder()).to(myExchangeOrder()).with("my.key");
    }




    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }
    /**
     * MQ发送方配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {

        // 若使用confirm-callback, 必须设置publisherConfirms为true
        connectionFactory.setPublisherConfirms(true);
        // 若使用return-callback, 必须设置publisherReturns为true
        connectionFactory.setPublisherReturns(true);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 使用return-callback, 必须设置mandatory为true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(messageConverter());

        // 如果消息没有到exchange, 则confirm回调, ack=false; 如果消息到达exchange, 则confirm回调, ack=true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            if (ack) {
                log.info("消息确认到达exchange!");
                /**
                 *
                 * 删除缓存在redis中的消息(保证消息不丢失，可以在发送消息的时候缓存在redis中)
                 * RedisUtils.del(correlationData.getId());
                 */

            } else {
                log.error("消息推送MQ Exchange失败, 请检查MQ是否异常, 消息ID: {}", correlationData.getId());
            }
        });

        // 如果exchange到queue成功, 则不回调return; 如果exchange到queue失败, 则回调return(需设置mandatory=true, 否则不会回调, 消息就丢了)
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

            log.error("消息推送MQ Queue失败, 请检查路由键准确, 交换机:{}, 路由键:{}", exchange, routingKey);

            /**
             * 重试机制
             * 1、消息反序列化
             * Object messageObj = new Jackson2JsonMessageConverter().fromMessage(message);
             * 2、休眠
             * 3、重新发送到mq
             * RabbitMQUtils.sendMessageToMQ(exchange, routingKey, messageObj);
             */


        });

        return rabbitTemplate;
    }

}
