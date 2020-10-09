package com.kuake.springboot.dao.mapper;

import com.kuake.springboot.entity.Department;

import java.util.List;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
public interface DepartmentMapper {
    List<Department> findAll();
}
