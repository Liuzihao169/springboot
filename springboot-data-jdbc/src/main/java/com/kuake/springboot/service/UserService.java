package com.kuake.springboot.service;

import com.kuake.springboot.entity.MyTransactional;
import com.kuake.springboot.util.MyjdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzihao
 * @create 2020/2/19-13:14
 */
@Service
public class UserService {
    @Autowired
    MyjdbcTemplate myjdbcTemplate;

    @MyTransactional
    public void insert() throws Exception {

        // ---------业务处理----------
        myjdbcTemplate.execute("INSERT INTO department VALUES (2,'开发部门')");
        int i = 1/0;
        myjdbcTemplate.execute("INSERT INTO tbl_user VALUES (2,'一号程序员')");

    }
}
