package com.kuake.srpingboot.listener;

import com.kuake.srpingboot.bean.Employee;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author hao
 * @create 2019-06-10 ${TIM}
 */
@Component
public class QueueListener{
    //可以监听多个queue
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "lister-test", durable = "true"),
                    exchange = @Exchange(value = "lister-exchange", type = ExchangeTypes.DIRECT, durable = "true"),
                    key = "key-listener"
            )}
    )
    public void receive(Map map){
        System.out.println("监听接收到消息"+map);
    }

    @RabbitListener(queues = {"my.amqpAdmin.queue"})
    /**
     * @Payload Employee employee  表示消息的载体 (也可不用注解标注)
     * @Header(AmqpHeaders.CHANNEL) 取Channel信息 (也可不用注解标注)
     * @Header(AmqpHeaders.DELIVERY_TAG) Long tag 获取消息的标志id
     * (tag 也可以在方法产生通过注入 Message 然后通过message.getMessageProperties().getDeliveryTag()进行获取)
     */
    public void getMessage(@Payload Employee employee,
                           @Header(AmqpHeaders.CHANNEL) Channel channel1,
                           @Header(AmqpHeaders.DELIVERY_TAG) Long tag) {
        System.out.println(employee);
    }
}
