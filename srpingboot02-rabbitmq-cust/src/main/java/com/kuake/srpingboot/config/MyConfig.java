package com.kuake.srpingboot.config;

        import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
        import org.springframework.amqp.support.converter.MessageConverter;
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
}
