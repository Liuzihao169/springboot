package com.kuake.springbootHelloworld.servlet;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author hao
 * @create 2019-06-02 ${TIM}
 */
public class MyServletContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("MyServletContextListener.....contextInitialized....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("MyServletContextListener....servletContextEvent....");
    }
}
