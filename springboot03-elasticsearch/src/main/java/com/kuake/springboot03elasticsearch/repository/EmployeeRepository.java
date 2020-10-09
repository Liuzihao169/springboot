package com.kuake.springboot03elasticsearch.repository;

import com.kuake.springboot03elasticsearch.bean.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;


/**
 * @author hao
 * @create 2019-06-12 ${TIM}
 */
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
    List<Employee> findByAboutLike(String name);
    //

}
