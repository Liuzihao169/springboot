package com.kuake.srpingboot.listener;

import com.kuake.srpingboot.bean.Employee;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
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
    @RabbitListener(queues = {"my.amqpAdmin.queue"})
    /**
     * @Payload Employee employee  表示消息的载体 (也可不用注解标注)
     * @Header(AmqpHeaders.CHANNEL) 取Channel信息 (也可不用注解标注)
     * @Header(AmqpHeaders.DELIVERY_TAG) Long tag 获取消息的标志id
     * (tag 也可以在方法产生通过注入 Message 然后通过message.getMessageProperties().getDeliveryTag()进行获取)
     */
    public void getMessage(@Payload Employee employee,
                           @Header(AmqpHeaders.CHANNEL) Channel channel1,
                           @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws Exception{
        /**
         * 假设当消息Employee age为5的时候拒绝消息
         */
        if (employee.getAge() == 5) {
            /**
             * @param long deliveryTag 消息的标志
             * @param boolean multiple 是否批量处理
             * @param boolean requeue  是否重入队列
             */
            System.out.println("消费者，拒绝消息" + employee);
            channel1.basicNack(tag, false, false);
            /**
             * 要想重试机制有，需要抛出异常
             */
            throw new RuntimeException("111");
        }else {
            /**
             * @param long deliveryTag 消息的标志
             * @param boolean multiple 是否批量处理
             */
            channel1.basicAck(tag, false);
            System.out.println("消费者，成功处理应答==>"+employee);

        }
    }
}
