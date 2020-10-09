package com.kuake.springboot.controller;

import com.kuake.springboot.entity.Department;
import com.kuake.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hao
 * @create 2019-06-07 ${TIM}
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dep/{id}")
    @ResponseBody
    public Department  getDep(@PathVariable Integer id){
        Department department = departmentService.selectById(id);
        return department;
    }

    @GetMapping("/dep")
    @ResponseBody
    public Department update(Department department){
        Department update = departmentService.update(department);
        return update;
    }

    @ResponseBody
    @GetMapping("/deletedep/{id}")
    public  String delete(@PathVariable Integer id){
        departmentService.delete(id);
        return "success";
    }
}
