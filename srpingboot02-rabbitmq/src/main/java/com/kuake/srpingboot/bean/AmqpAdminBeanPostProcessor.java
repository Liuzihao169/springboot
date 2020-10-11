package com.kuake.srpingboot.bean;

import org.springframework.amqp.core.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liuzihao
 * @create 2020-10-09-22:04
 */
@Component
public class AmqpAdminBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("amqpAdmin")) {
            AmqpAdmin amqpAdmin = (AmqpAdmin)bean;
            amqpAdmin.declareQueue(testQueue());
            amqpAdmin.declareExchange(myExchange());
            amqpAdmin.declareBinding(binding());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private Queue testQueue() {
        return new Queue("test-admin1-processor1");
    }
    private DirectExchange myExchange() {
        return new DirectExchange("directexchange-admin1-processor1");
    }
    private Binding binding() {
        return  BindingBuilder.bind(testQueue()).to(myExchange()).with("test-key-admin1-processor1");
    }

}
