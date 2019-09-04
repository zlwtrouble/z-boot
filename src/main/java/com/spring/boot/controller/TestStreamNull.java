package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.spring.boot.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/9/2 11:19
 **/
public class TestStreamNull {

     public static void main(String[] args) {
         List<Student> list = StudentList.getStudentList();

         Student s1 = new Student();
         s1.setId(1);
         s1.setName("学生11");
         Student s2 = new Student();
         s2.setId(2);
         s2.setName("学生22");

         Student s3 = new Student();
         s3.setName("学生33");

         s3.setId(null);
         list.add(s1);
         list.add(s2);
         list.add(s3);
         list.add(null);

         System.out.println(""+ JSON.toJSONString(list));

        // Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, v -> v));
         Map<Integer, Student> map = list.stream().filter(Objects::nonNull).collect(Collectors.toMap(Student::getId, v -> v, (k1, k2) -> k2));

         System.out.println(""+ JSON.toJSONString(map));

         Map<Integer, Student> map2 = list.stream().filter(o->o!=null &&o.getId()!=null).collect(Collectors.toMap(Student::getId, v -> v, (k1, k2) -> k2));

         System.out.println(""+ JSON.toJSONString(map2));

     }
}
