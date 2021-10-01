package com.example.springboottest.controller;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author liuzihao
 * @create 2021-04-20-19:31
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() throws Exception{
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class<?> clazz = groovyClassLoader.parseClass("package com.example.springboottest.groovy\n" +
                "\n" +
                "class Hello {\n" +
                "    public String tell(){\n" +
                "        return \"im groovy\"\n" +
                "    }\n" +
                "}\n");
        Object o = clazz.newInstance();
        Method tell = clazz.getMethod("tell");
        return (String) tell.invoke(o);

    }

    @RequestMapping("/hello1")
    @ResponseBody
    public String hello1() throws Exception{
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        URL resource = this.getClass().getClassLoader().getResource("groovy/Hello.groovy");
        Class aClass = groovyClassLoader.parseClass(new GroovyCodeSource(resource));
        Object o = aClass.newInstance();

        Method tell = aClass.getMethod("tell");
        return (String) tell.invoke(o);

    }
}
