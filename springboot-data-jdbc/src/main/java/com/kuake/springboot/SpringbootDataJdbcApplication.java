package com.kuake.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringbootDataJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDataJdbcApplication.class, args);
	}

}
