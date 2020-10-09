package com.kuake.springboot.controller;

import com.kuake.springboot.dao.MapperDao;
import com.kuake.springboot.dao.mapper.DepartmentMapper;
import com.kuake.springboot.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
@Controller
public class HelloController {

    //注解版
    @Autowired
    private MapperDao mapperDao;

    //配置版本
    @Autowired
    private DepartmentMapper departmentMapper;

    @ResponseBody
    @GetMapping("/find/{id}")
    public Department find(@PathVariable Integer id){
        System.out.println(mapperDao+"============================>");
        Department byId = mapperDao.findById(id);
        return  byId;
    }


    @GetMapping("/finds")
    @ResponseBody
    public List<Department> findAll(){
        System.out.println(departmentMapper+"================================>");
        List<Department> all = departmentMapper.findAll();
        return all;
    }
}
