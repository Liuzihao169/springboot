package com.kuake.springboot.entity;

/**
 * @author liuzihao
 * @create 2020/2/19-14:10
 */

import com.kuake.springboot.service.UserService;
import com.kuake.springboot.util.MyjdbcTemplate;
import org.apache.catalina.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.sql.Connection;
@Component
@Aspect
public class MyAspectj {

    @Autowired
    MyjdbcTemplate myjdbcTemplate;
    @Autowired
    UserService userService;

    @Around("@annotation(MyTransactional)")
    public void doTran(ProceedingJoinPoint joinPoint) throws  Exception{

        System.out.println("====开始事务====");
        Connection con = myjdbcTemplate.getConnection();
        con.setAutoCommit(false);
        try {
            // 执行目标方法 也就是doTrn()方法
            joinPoint.proceed();
            con.commit();
            System.out.println("====事务提交成功====");


        }catch (Exception ex){
            System.out.println("====发生异常事务回滚===");
            con.rollback();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
