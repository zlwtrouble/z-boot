package com.spring.boot.controller;

import com.spring.boot.entity.Student;

public class TestNull {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName(null);
        System.out.println(student.getName() + "sdfasdf");
    }
}
