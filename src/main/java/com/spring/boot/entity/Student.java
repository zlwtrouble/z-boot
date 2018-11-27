package com.spring.boot.entity;

import lombok.Data;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/22 11:05
 **/
@Data
public class Student implements Comparable<Student> {


    private Integer id;
    private String name;
    private Integer age;

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }




    @Override
    public int compareTo(Student o) {
        return age.compareTo(o.getAge());
    }


}

