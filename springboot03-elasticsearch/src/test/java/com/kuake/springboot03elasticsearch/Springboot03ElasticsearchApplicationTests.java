package com.kuake.springboot03elasticsearch;

import com.kuake.springboot03elasticsearch.bean.Book;
import com.kuake.springboot03elasticsearch.bean.Employee;
import com.kuake.springboot03elasticsearch.repository.BookRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;
import com.kuake.springboot03elasticsearch.repository.EmployeeRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticsearchApplicationTests {

    @Autowired
    BookRepository bookRepository;
	@Autowired
    ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
    EmployeeRepository employeeRepository;
//    @Autowired
//    JestClient jestClient;

	@Test
	public void contextLoads() {
//		elasticsearchTemplate.putMapping("baidu","emp","this is a baidu empyloee");
        //创建一个员工
//        Employee employee = new Employee("liu","zihao",21,"he is a boy", Arrays.asList("playing","codeing"));
        /**
         *
         *   Relational DB -> Databases -> Tables -> Rows -> Columns
         *   Elasticsearch -> Indices   -> Types  -> Documents -> Fields
         */
        // 创建一个索引
       /* Index build = new Index.Builder(employee).index("baidu").type("emp").id("007").build();

        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }  */
    }

    /**
     *
     * 自己要尝试看官方文档，不要照着视频一步一步的去写
     * @throws IOException
     */
/*
    @Test
    public void testget() throws IOException {
        //索引文档 (动词)
        //创建一个查询action
        String index = "baidu";
        String id = "007";
        Get build = new Get.Builder(index, id).build();
        DocumentResult execute = jestClient.execute(build);
        String jsonString = execute.getJsonString();
        System.out.println(jsonString);
    }
*/
    @Test
    public void test02(){
//        elasticsearchTemplate.putMapping("baidu","emp","this is a baidu empyloee");
        Employee employee = new Employee("liu","zihao",21,"he is a girl", Arrays.asList("playing1","codeing1"));

        //创建一个indexQuery
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("002");
      //  indexQuery.setIndexName("alibaba");
        indexQuery.setType("emp");
        /**
         *       Relational DB -> Databases -> Tables -> Rows -> Columns
         *       Elasticsearch -> Indices   -> Types  -> Documents -> Fields
         */
        indexQuery.setObject(employee);
        /**
         * public void bulkIndex(List<IndexQuery> queries) 可以创建一个list 进行批量索引
         */
        String index = elasticsearchTemplate.index(indexQuery);
        System.out.println("创建成功================>"+index);

    }

    //进行索引查找
    @Test
    public void test03search(){
        String query = "/alibaba/emp/_search?q=first_name:liu";
//        StringQuery stringQuery = new StringQuery(query);
//        List<Employee> employees = elasticsearchTemplate.queryForList(stringQuery, Employee.class);
//        System.out.println(employees);
//        elasticsearchTemplate.queryForList(query,Employee.class);
//        elasticsearchTemplate.queryForList(query,Employee.class);
        NativeSearchQuery alibaba = new NativeSearchQueryBuilder().withQuery( QueryBuilders.termQuery("age","21")).build();

        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();

        NativeSearchQuery build1 = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("age", "21")).build();
        NativeSearchQuery build2 = new NativeSearchQueryBuilder().withQuery(queryStringQuery("l")).build();
        List<Employee> employees = elasticsearchTemplate.queryForList(alibaba, Employee.class);
        System.out.println(employees);
    }

        @Test
        public void test04(){
           /* Employee employee = new Employee("liu","zihao",18,"jjjj", Arrays.asList("playing1","codeing1"));
            employee.setId("000000000");
            employeeRepository.index(employee);*/
           List<Employee> liu = employeeRepository.findByAboutLike("jjjj");
            System.out.println(liu);
        }

        @Test
        public void test05(){
            Book book = new Book("001","abc","cde");
            String index = elasticsearchTemplate.index(new IndexQueryBuilder().withObject(book).build());
            System.out.println(index);
        }

        @Test
    public void test06(){
            /**
             * 实体类要设置 id
             */
            List<Book> a = elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("author","cde")).build(), Book.class);
            List<Book> b = elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build(), Book.class);
            List<Book> c = elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("cde")).build(), Book.class);
            System.out.println(c);
        }

        @Test
    public void test07(){
//            bookRepository.index(new Book("002","jjjj","d"));
            List<Book> d = bookRepository.findByAuthorContaining("d");
            System.out.println(d);
        }
}
