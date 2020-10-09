package com.kuake.spingboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author hao
 * @create 2019-06-12 ${TIM}
 */
@Service
public class AsyncService {
    @Async
    public  String hello() throws InterruptedException {
        System.out.println("当前线程名字==================>"+Thread.currentThread().getName());
        Thread.sleep(5000);
        return "hello";
    }

    //定时任务 (异步)

    /**
     * second  minute, hour, day of month, month , day of week, (year)
     * <p>E.g. {@code "0 * * * * MON-FRI"}
     */
    @Scheduled(cron = "0-10 * * * * *")
    public void myTask(){
        System.out.println("hello==========>当前的线程"+Thread.currentThread().getName());
    }
}
