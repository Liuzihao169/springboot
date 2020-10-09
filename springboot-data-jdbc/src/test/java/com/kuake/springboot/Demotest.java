package com.kuake.springboot;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hao
 * @create 2019-06-04 ${TIM}
 */
public class Demotest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
        Date parse = simpleDateFormat.parse("2019-06-04");
        System.out.println(parse);
    }
}
