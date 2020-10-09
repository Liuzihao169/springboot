package com.kuake.srpingboot.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzihao
 * @create 2020-10-09-17:30
 */
@Configuration
public class RabbitMqAutoAdminConfig {

    @Autowired
    ApplicationContext context;

    @Bean
    public AmqpAdmin admin() {
        AmqpAdmin bean = context.getBean(AmqpAdmin.class);
        System.out.println("bean");
        return bean;
    }
}
