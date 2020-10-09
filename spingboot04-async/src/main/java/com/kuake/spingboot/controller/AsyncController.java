package com.kuake.spingboot.controller;

import com.kuake.spingboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hao
 * @create 2019-06-12 ${TIM}
 */
@Controller
public class AsyncController {

    @Autowired
    private AsyncService service;

    @ResponseBody
    @RequestMapping("/hello")
    public  String hello() throws InterruptedException {
        System.out.println("当前的线程===============>"+Thread.currentThread().getName());
        service.hello();
        return  "async controller";
    }
}
