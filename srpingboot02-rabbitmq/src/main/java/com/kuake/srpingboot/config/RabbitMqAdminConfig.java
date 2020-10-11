package com.kuake.srpingboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzihao
 * @create 2020-10-09-11:07
 */
//@Configuration
public class RabbitMqAdminConfig {

    public Queue testQueue() {
        return new Queue("my.amqpAdmin.queue");
    }
    public DirectExchange myExchange() {
        return new DirectExchange("my.direct");
    }
    public Binding binding() {
        return  BindingBuilder.bind(testQueue()).to(myExchange()).with("my.key");
    }


    // @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        AmqpAdmin amqpAdmin =  new RabbitAdmin(connectionFactory);
        amqpAdmin.declareQueue(testQueue());
        amqpAdmin.declareExchange(myExchange());
        amqpAdmin.declareBinding(binding());
        return amqpAdmin;
    }
}
