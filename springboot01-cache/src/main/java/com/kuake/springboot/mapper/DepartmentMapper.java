package com.kuake.springboot.mapper;

import com.kuake.springboot.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author hao
 * @create 2019-06-07 ${TIM}
 */
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id =#{id}")
     Department selectById(Integer id);

    @Delete("DELETE  FROM department WHERE id=#{id}")
    void deleteById(Integer id);

    @Update("UPDATE department SET  departmentName =#{departmentName} WHERE id =#{id}")
    void update(Department department);

    @Insert("INSERT INTO department(departmentName) VALUES (#{departmentName})")
    void insert(Department department);
}
