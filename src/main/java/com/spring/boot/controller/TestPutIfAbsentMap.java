package com.spring.boot.controller;

import com.spring.boot.entity.Student;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/26 16:17
 **/
public class TestPutIfAbsentMap {

     public static void main(String[] args) {
         ConcurrentHashMap<String, Student> pool = new ConcurrentHashMap<String, Student>();
         Student student1 = new Student();
         student1.setId(0);
         pool.putIfAbsent("1",student1);
         Student student2 = new Student();
         student2.setId(22);
         Student student = pool.putIfAbsent("1", student2);
         System.out.println(""+student.getId());
     }
}
