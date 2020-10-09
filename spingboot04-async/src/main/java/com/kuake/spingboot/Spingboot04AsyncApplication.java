package com.kuake.spingboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//开启基于注解的定时任务
@SpringBootApplication
@EnableAsync//开启支持异步请求
public class Spingboot04AsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spingboot04AsyncApplication.class, args);
	}

}
