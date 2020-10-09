package com.kuake.springboot03elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot03ElasticsearchApplication {
	/**
	 *
	 * springboot 操作Elasticsearch有两种方式
	 * 	1.Jest(默认不生效)
	 *
	 * 	2.spring-data-Elasticsearch
	 * 	自动配置了
	 * 		1) 、Client(根据是否配置了集群节点来，选择创建哪种类型的客户端)
	 * 		2) 、ElasticsearchTemplate 操作模板
	 * 		启动报错，因为版本不适配 2.1.x===2.4.0
	 * 1、给
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Springboot03ElasticsearchApplication.class, args);
	}

}
