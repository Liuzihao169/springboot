package com.kuake.springboot03elasticsearch.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hao
 * @create 2019-06-11 ${TIM}
 */
@Document(indexName = "alibaba")
public class Employee {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        @Id
       private  String id;
        private String first_name;
        private String  last_name;
        private int age;
        private String  about;
        List<String>interets = new ArrayList<>();

    public Employee() {
    }

    public Employee(String first_name, String last_name, int age, String about, List<String> interets) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.about = about;
        this.interets = interets;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getInterets() {
        return interets;
    }

    public void setInterets(List<String> interets) {
        this.interets = interets;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", about='" + about + '\'' +
                ", interets=" + interets +
                '}';
    }
}
