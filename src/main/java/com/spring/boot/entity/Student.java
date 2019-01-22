package com.spring.boot.entity;

import com.spring.boot.annotation.FieldNameAnnotation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/22 11:05
 **/
@Data
public class Student implements Comparable<Student> {

    @FieldNameAnnotation(name="ID")
    private Integer id;

    @FieldNameAnnotation(name="姓名")
    private String name;

    private Integer age;

    @FieldNameAnnotation(name="钱包")
    private BigDecimal  wallet;

    @FieldNameAnnotation(name="生日")
    private Date birthdate;

    public Student() {
    }

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

