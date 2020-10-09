package com.kuake.springbootHelloworld.exception;

/**
 * @author hao
 * @create 2019-06-01 ${TIM}
 */
public class MyException extends  RuntimeException {
    public MyException() {
        super("自定义异常.....");
    }
}
