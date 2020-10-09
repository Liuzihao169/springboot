package com.kuake.springbootHelloworld.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author hao
 * @create 2019-06-02 ${TIM}
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("this is  my fliter .....");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
