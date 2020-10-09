package com.kuke.starter.kuakespringboot;

/**
 * @author hao
 * @create 2019-06-06 ${TIM}
 */
public class HelloService {

    private  HelloProperties helloProperties;
    public String sayHello(String name){
        return helloProperties.getPrefix()+name+helloProperties.getSuffix();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
