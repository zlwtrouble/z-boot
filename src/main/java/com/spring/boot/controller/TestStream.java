package com.spring.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.spring.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/22 11:06
 **/
@Slf4j
public class TestStream {
     public static void main(String[] args) {
         List<Student> studentList= Arrays.asList(new Student(1,"ziwen1",10),new Student(2,"aiwen2",18),new Student(3,"biwen3",28));
         log.info("【测试流】原始【{}】", JSONObject.toJSONString(studentList));
         //自然序列
         List<Student> studentList1=studentList.stream().sorted().collect(Collectors.toList());
        log.info("【测试流】自然排序-升序【{}】",JSONObject.toJSONString(studentList1));
         //逆序
         List<Student> studentList2=studentList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
         log.info("【测试流】降序【{}】",JSONObject.toJSONString(studentList2));

         List<Student> studentList3= Arrays.asList(new Student(1,"ziwen1",10),new Student(2,"aiwen2",18),new Student(3,"biwen3",28),new Student(4,"asdfbiwen3",28),new Student(5,"asdfdsbiwen3",28));

         studentList3.forEach(o-> {
             System.out.println("流foreach"+o);
         });

         //list转map
         List<Long> listForeach= Lists.newArrayList();
         Map<Integer, Student> collect = studentList3.stream().collect(Collectors.toMap(Student::getId, student -> student));
         System.out.println(collect);

         studentList3.forEach(o->{
             System.out.println("普通流"+o);
         });

         studentList3.parallelStream().forEach(o->{
             System.out.println("并行流"+o);
         });

         List<Student> collectFilter = studentList3.parallelStream().filter(o-> o.getId().equals(1)).collect(Collectors.toList());

         System.out.println("并行过滤流"+collectFilter);

     }
}
