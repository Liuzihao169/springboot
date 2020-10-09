package com.kuake.springboot;

import com.kuke.starter.kuakespringboot.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hao
 * @create 2019-06-06 ${TIM}
 */
@Controller
public class helloController {

    @Autowired
    private HelloService helloService;
    @ResponseBody
    @GetMapping("/hello")
    public String helloService(){
        String jack = helloService.sayHello("jack");
        return jack ;
    }
}
