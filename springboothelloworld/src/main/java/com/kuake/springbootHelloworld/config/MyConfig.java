package com.kuake.springbootHelloworld.config;

import com.kuake.springbootHelloworld.servlet.MyFilter;
import com.kuake.springbootHelloworld.servlet.MyServlet;
import com.kuake.springbootHelloworld.servlet.MyServletContextListener;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author hao
 * @create 2019-06-02 ${TIM}
 */
@Configuration
public class MyConfig {


    /**
     * 自己定制servlet容器的，属性，端口号，项目访问路径。属性配置文件的底层也是这种方式设置的。
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer (){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8088);
            }
        };
    }


    //注册三组件
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        // 设置filter
        servletRegistrationBean.setServlet(new MyServlet());
        //设置url
        servletRegistrationBean.setUrlMappings(Arrays.asList("/myservlet","/myservlet2"));
        return  servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myservlet","/hello"));
        return filterRegistrationBean;
    }

    @Bean
    public  ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<MyServletContextListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(new MyServletContextListener());
        return bean;
    }
 }
