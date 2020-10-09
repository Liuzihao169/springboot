package com.kuake.springboot.contorller;

import com.kuake.springboot.entity.User;
import com.kuake.springboot.repository.UserRepository;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hao
 * @create 2019-06-05 ${TIM}
 */
@Controller
public class HelloController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping("/all")
    public List<User> findAll(){
        System.out.println(userRepository+"=========================>");
        List<User> allBy = userRepository.findAllBy();
        return allBy;
    }
}
