package com.kuake.springboot.config;

import com.kuake.springboot.entity.Department;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author hao
 * @create 2019-06-08 ${TIM}
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Department> redisTemplateDep(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
       /* ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class type = (Class) genericSuperclass.getActualTypeArguments()[0];
        System.out.println(type);*/
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        Jackson2JsonRedisSerializer<Department> joson = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(joson);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     *
     * 自定义cache Manage
     */

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, Department> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        Jackson2JsonRedisSerializer<Department> joson = new Jackson2JsonRedisSerializer<Department>(Department.class);
        redisTemplate.setDefaultSerializer(joson);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
