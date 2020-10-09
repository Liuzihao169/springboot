package com.kuke.starter.kuakespringboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hao
 * @create 2019-06-06 ${TIM}
 */
@ConfigurationProperties(prefix = "kuake.hello")
public class HelloProperties {
    private String prefix;
    private  String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
