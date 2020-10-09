package com.kuake.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author liuzihao
 * @create 2020/2/19-13:16
 */
@Component
public class MyjdbcTemplate {

    @Autowired
    DataSource source;

    ThreadLocal<Connection> connection = new ThreadLocal<>();

    // 控制只有一个连接
    public Connection getConnection() throws Exception{
        if (connection.get() == null){
            connection.set(source.getConnection());
        }
        return connection.get();
    }

    public void execute(String sql) throws Exception{
        // 获得连接
        Connection connection = source.getConnection();
        // 获得sql操作对象平台
        Statement statement = connection.createStatement();
        // 执行sql语句
        statement.executeUpdate(sql);
    }
}
