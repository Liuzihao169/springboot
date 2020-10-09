package com.kuake.springboot;

import com.kuake.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDataJdbcApplicationTests {

	@Autowired
	DataSource source;

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
    UserService userService;

	@Test
    @Transactional
	public void contextLoads() throws SQLException {
		// 没有insert操作 只有update
		jdbcTemplate.update("INSERT INTO department(departmentName) VALUES(?)","开发部门");
		int i = 1/0;
		jdbcTemplate.update("INSERT INTO tbl_user(user_name) VALUES(?)","程序员");
	}

	@Test
	public void test2(){
		System.out.println(source);
	}

	//
/*	@Test
    public void testOrg() throws SQLException {
	    // 获得连接
        Connection connection = source.getConnection();
        // 获得sql操作对象平台
        Statement statement = connection.createStatement();
        // 执行sql语句
        statement.executeUpdate("INSERT INTO department VALUES (1,'事业部门')");
        statement.executeUpdate("INSERT INTO tbl_user VALUES (1,'老刘员工')");
        // 关闭连接
        connection.close();
    }*/

    @Test
    public void testOrgWithTran() throws SQLException {
        // 获得连接
        Connection connection = source.getConnection();
        // 获得sql操作对象平台
        Statement statement = connection.createStatement();
        // 设置手动提交事务
        connection.setAutoCommit(false);
        // 执行业务逻辑操作
        try {
            statement.executeUpdate("INSERT INTO department VALUES (2,'人事部门')");
            int i = 1/0;
            statement.executeUpdate("INSERT INTO tbl_user VALUES (2,'人事1号员工')");
        }catch (Exception ex){
            // 回滚操作
            connection.rollback();
        }

        // 关闭连接
        connection.close();
    }


    @Test
    public void testTran() throws Exception {
        // 改userService 是被代理的
        userService.insert();
    }
}
