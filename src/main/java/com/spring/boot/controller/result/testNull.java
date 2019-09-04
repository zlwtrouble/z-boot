package com.spring.boot.controller.result;

import com.spring.boot.entity.Student;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/17 17:21
 **/
public class testNull {

     public static void main(String[] args) {
         Student student = new Student();
         System.out.println(""+student.getName());
      }
}
