package com.kuake.srpingboot.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author hao
 * @create 2019-06-10 ${TIM}
 */
@Component
public class QueueListener{
    //可以监听多个queue
    @RabbitListener(queues = {"my.amqpAdmin.queue"})
    public void receive(Map map){
        System.out.println("监听接收到消息"+map);
    }
}
