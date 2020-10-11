package com.kuake.srpingboot;

import com.kuake.srpingboot.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Srpingboot02RabbitmqApplicationTests {

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
	private  AmqpTemplate amqpTemplate;

	@Test
	public void contextLoads() {
		/*
		* Exchange是一个接口实现类如下：
		* 	1.DirectExchange
		* 	2.FanoutExchange
		* 	3.TopicExchange
		* */
		//创建类型是Direct name=my.direct的交换机
		Exchange exchange = new DirectExchange("my.direct");
		amqpAdmin.declareExchange(exchange);

		//创建一个name=my.amqpAdmin.queue的队列
		/*
         * Queue中可以设置的属性：
         * String name, 名字
         * boolean durable, 持久化
         * boolean exclusive, 声明一个独占队列
         * boolean autoDelete, 是否自动删除
         * Map<String, Object> arguments 用于声明队列的参数
         *
         * */
		amqpAdmin.declareQueue(new Queue("my.amqpAdmin.queue"));

		//队列跟交换机进行绑定
		/**
		 * Binding可以设置的参数：
		 * 	destination  目的地
		 * 	destinationType 绑定的类型 是跟队列绑定 还是交换机绑定
		 * 	exchange 交换机的name
		 * 	routingKey 路由的key
		 * 	arguments; 参数 可以不设置
		 *
		 */
		Binding binding = new Binding("my.amqpAdmin.queue", Binding.DestinationType.QUEUE,"my.direct","my.routing",null);
		amqpAdmin.declareBinding(binding);
	}

	@Test
	public void testsend(){
		/**
		 * exchange: 使用发送消息的交换机
		 * routingKey: 发送消息使用路由的key
		 * message: 需要发送的消息
		 *
		 */

//		amqpTemplate.convertAndSend("my.direct","my.routing","hello world !");
		List<Object> list=new ArrayList<>();
		list.add(1);
		list.add("abc");
		amqpTemplate.convertAndSend("my.direct","my.routing",list);
	}

	@Test
	public void testrecive(){
		//指定队列的名字，进行接收
		Object o = amqpTemplate.receiveAndConvert("my.amqpAdmin.queue");
		System.out.println(o);
	}

    /**
     * 测试发送消息10条
     */
	@Test
	public void testsendObject(){
		/**
		 * exchange: 使用发送消息的交换机
		 * routingKey: 发送消息使用路由的key
		 * message: 需要发送的消息
		 *
		 */

		for(int i=0;i<10;i++){
            Employee employee = new Employee("hello"+i, i, "我是消息"+i);
			amqpTemplate.convertAndSend("my.direct","my.routing",employee);
		}
	}

    /**
     *
     * 启动容器 测试：初始化队列或交换机的时候使用
     */
	@Test
    public void test(){

    }
}
