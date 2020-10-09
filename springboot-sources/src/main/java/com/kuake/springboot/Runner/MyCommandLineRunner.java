package com.kuake.springboot.Runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("======================>MyCommandLineRunner............run"+ Arrays.asList(args));
    }
}
