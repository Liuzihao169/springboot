package com.kuake.springboot.dao;

import com.kuake.springboot.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
//@Mapper//指定这是一个操作数据库的mapper
public interface MapperDao {

    @Select("select * from department where id =#{id}")
    Department findById(@Param(value ="id" ) Integer id);

}
