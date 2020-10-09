package com.kuake.springbootHelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(value = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringboothelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboothelloworldApplication.class, args);
	}

}
