package com.kuake.srpingboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzihao
 * @create 2020-10-08-22:13
 *
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("test3");
    }
    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange("directexchange3");
    }
    @Bean
    public  Binding binding() {
        return  BindingBuilder.bind(testQueue()).to(myExchange()).with("test-key3");
    }
}
