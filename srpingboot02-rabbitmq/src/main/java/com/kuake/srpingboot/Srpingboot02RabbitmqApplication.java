package com.kuake.srpingboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class Srpingboot02RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Srpingboot02RabbitmqApplication.class, args);
	}

}
