package com.kuake.springbootHelloworld.controller;

import com.kuake.springbootHelloworld.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hao
 * @create 2019-06-01 ${TIM}
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public String handlerException(HttpServletRequest request, Exception e){
        System.out.println("handlerException....");
        Map<String,Object> map = new HashMap<String, Object>();
        request.setAttribute("javax.servlet.error.status_code",400);
        map.put("message",e.getMessage());
        map.put("code","this is a exception");
        map.put("aaa","bbb");

        //到自定义DefaultErrorAttributes中取出来然后，添加额外定制的消息 然后在返回
        request.setAttribute("ext",map);

        //返回给BasicErrorController处理
        return "forward:/error";
    }
}
