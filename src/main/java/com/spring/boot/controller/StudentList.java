package com.spring.boot.controller;

import com.spring.boot.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/9/2 11:23
 **/
public class StudentList {

    public static  List<Student> getStudentList(){

        List<Student> array = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("学生1");
        Student s2 = new Student();
        s2.setId(2);
        s2.setName("学生2");

        Student s3 = new Student();
        s3.setName("学生3");

        s3.setId(null);
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(null);

        return array;
    }



}
