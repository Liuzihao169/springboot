package com.kuake.springboot.service;

import com.kuake.springboot.entity.Department;
import com.kuake.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author hao
 * @create 2019-06-07 ${TIM}
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = "dep")
    public Department selectById(Integer id){
        System.out.println("从数据库中查询："+id+"部门");
        Department department = departmentMapper.selectById(id);
        return department;
    }

    /**
     * 执行方法之后，更新cache中 key的值。
     * 每一次方法都会执行
     * @param department
     * @return
     */
    @CachePut(cacheNames = "dep",key = "#department.id")
    public Department update(Department department){
        System.out.println("更新部门"+department.getId()+"号");
        departmentMapper.update(department);
        return  department;
    }

    /**
     * @CacheEvict 清空缓存
     *
     *
     */
    @CacheEvict(cacheNames = "dep",key = "#id")
    public void delete(Integer id){
        System.out.println("deleteById"+id+"部门");
       // departmentMapper.deleteById(id);
    }

    public  void insert(Department department){
        departmentMapper.insert(department);
    }
}
