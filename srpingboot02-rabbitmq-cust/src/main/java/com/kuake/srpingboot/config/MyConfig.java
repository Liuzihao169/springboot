package com.kuake.srpingboot.config;

        import org.springframework.amqp.core.*;
        import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
        import org.springframework.amqp.rabbit.connection.ConnectionFactory;
        import org.springframework.amqp.rabbit.core.RabbitTemplate;
        import org.springframework.amqp.rabbit.retry.MessageRecoverer;
        import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
        import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
        import org.springframework.amqp.support.converter.MessageConverter;
        import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

/**
 * @author hao
 * @create 2019-06-10 ${TIM}
 */
@Configuration
public class MyConfig {
    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // 手动应答
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 重试需要设置为false
        factory.setDefaultRequeueRejected(false);
        return factory;
    }


    /**
     * 创建回收队列
     * @return
     */
    @Bean
    public DirectExchange errorExchange(){
        return new DirectExchange("rec-exchange",true,false);
    }

    @Bean
    public Queue errorQueue(){
        return new Queue("rec-queue", true);
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, DirectExchange errorExchange){
        return BindingBuilder.bind(errorQueue).to(errorExchange).with("routing-key");
    }

    /**
     * 消息回收器
     * @param rabbitTemplate
     * @return
     */
    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate,"rec-exchange","routing-key");
    }
}
