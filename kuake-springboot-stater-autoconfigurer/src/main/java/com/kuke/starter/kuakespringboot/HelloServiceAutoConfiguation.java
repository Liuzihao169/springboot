package com.kuke.starter.kuakespringboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hao
 * @create 2019-06-06 ${TIM}
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnWebApplication //web环境才生效
public class HelloServiceAutoConfiguation {
    private HelloProperties helloProperties;

    //当只有一个构造参数的时候，参数列表会自动注入
    public  HelloServiceAutoConfiguation( HelloProperties helloProperties){
        this.helloProperties = helloProperties;
    }

    @Bean
    public HelloService service(){
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return  helloService;
    }
}
