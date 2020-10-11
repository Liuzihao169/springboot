package com.kuake.srpingboot.bean;

import java.util.Date;

/**
 * @author hao
 * @create 2019-06-10 ${TIM}
 */
public class Employee {
    private String name;
    private Integer age;
    private String desc;


    public Employee() {
    }

    public Employee(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirth() {
        return desc;
    }

    public void setBirth(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birth=" + desc +
                '}';
    }
}

