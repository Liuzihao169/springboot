package com.kuake.springboot.repository;

import com.kuake.springboot.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
public interface UserRepository extends Repository<User,Integer> {
    List<User> findAllBy();
}
