package com.kuake.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")//将属性进行绑定
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }
    //添加druid后台监控
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        //设置初始化参数
        Map<String,String> params = new HashMap<>();
        params.put("jmxUsername","admin");
        params.put("jmxPassword","admin");
        servletRegistrationBean.setInitParameters(params);
        servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        return  servletRegistrationBean;
    }

    //配置后台监控的过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.setInitParameters(initParams);

        return  filterRegistrationBean;
    }


}
