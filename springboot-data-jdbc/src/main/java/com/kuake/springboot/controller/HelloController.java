package com.kuake.springboot.controller;

import com.kuake.springboot.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public List<Department> helloQuery(){
        String sql ="SELECT * FROM department";
        //利用jdbc 模板查询
        List<Department> departments = jdbcTemplate.query(sql, new RowMapper<Department>() {
            @Override
            public Department mapRow(ResultSet resultSet, int i) throws SQLException {
                Department d = new Department();
                d.setId(resultSet.getInt("id"));
                d.setDepartmentName(resultSet.getString("departmentName"));
                return d;
            }
        });
        return departments;

    }
}
