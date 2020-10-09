package com.kuake.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hao
 * @create 2019-06-02 ${TIM}
 */
@Controller
public class HelloController {

//    @ResponseBody
    @RequestMapping("/hello")
    public String hell(){
        return "abc";
    }
}
