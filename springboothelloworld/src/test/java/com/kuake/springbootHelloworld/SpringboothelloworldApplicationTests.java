package com.kuake.springbootHelloworld;

import com.kuake.springbootHelloworld.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringboothelloworldApplicationTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Student student;

	@Resource(name = "StudentByxml")
	private  Student studentByxml;

	@Test
	public void contextLoads() {
		System.out.println(studentByxml);
	}

	@Test
	public void testlog(){
		// 日志级别由低到高，springboot默认的root级别 也就是info级别
	logger.trace("trace===>1");
	logger.debug("debug====>2");
	logger.info("info=====>3");
	logger.warn("warn=====>4");
	logger.error("error======>5");

	}

}
