package com.kuake.springbootHelloworld.controller;

import com.kuake.springbootHelloworld.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author hao
 * @create 2019-05-30 ${TIM}
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello...springboot";
    }

    @RequestMapping("/index")
    public String tohello(Map<String,Object>map){
        //class org.springframework.validation.support.BindingAwareModelMap
        System.out.println(map.getClass());
        map.put("hello","<h1>hello</h1>");
        map.put("lists", Arrays.asList("a","b","c","d"));
        return "hello";
    }

    @RequestMapping("/testerror")
    public String testerror(Integer a) throws  Exception{
        if(a==111){
            throw new MyException();
        }
        return  "hello";
    }
}
