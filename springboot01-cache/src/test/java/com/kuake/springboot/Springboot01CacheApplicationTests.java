package com.kuake.springboot;

import com.kuake.springboot.entity.Department;
import com.kuake.springboot.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

	@Qualifier(value = "redisTemplateDep")
	@Autowired
	private RedisTemplate<Object, Department> redisTemplatdep;

	@Autowired
	private  RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private DepartmentMapper departmentMapper;
	@Test
	public void contextLoads() {
		Department department = departmentMapper.selectById(1);
		System.out.println(department);
	}

	/**
	 * redis的五种数据结构 String  list  hash set sort-set
	 *
	 */
	@Test
	public void testRedis(){
		//String操作
//		String age = stringRedisTemplate.opsForValue().get("age");
//		System.out.println(age);
//		stringRedisTemplate.opsForValue().set("username","小强");

		//list操作
//		stringRedisTemplate.opsForList().leftPushAll("mylist", Arrays.asList("a","b","c"));

		//hash操作
//		Object o = stringRedisTemplate.opsForHash().get("my1", "userbname");
//		System.out.println(o);
//
//		Boolean aBoolean = stringRedisTemplate.opsForHash().hasKey("my1", "userbname");
//		System.out.println(aBoolean.booleanValue());

		//set操作
//		stringRedisTemplate.opsForSet().add("myset","a","b","c");
//
//		//sort-set
//		Long mysort = stringRedisTemplate.opsForZSet().count("mysort", 100, 300);
//		System.out.println(mysort);

	}

	@Test
	public void test2(){
		Department department = new Department();
		department.setId(001);
		department.setDepartmentName("部门名");
		redisTemplatdep.opsForValue().set("dep-1",department);

	}

	@Test
	public void test3(){
		Department department = redisTemplatdep.opsForValue().get("dep-1");
		System.out.println(department);
	}
}
