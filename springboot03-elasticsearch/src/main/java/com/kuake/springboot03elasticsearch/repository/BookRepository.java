package com.kuake.springboot03elasticsearch.repository;

import com.kuake.springboot03elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author hao
 * @create 2019-06-12 ${TIM}
 */
public interface BookRepository extends ElasticsearchRepository<Book,String> {
    List<Book> findByAuthorContaining(String name);
}
