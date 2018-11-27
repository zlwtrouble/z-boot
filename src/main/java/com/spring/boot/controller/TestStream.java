package com.spring.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
         List<Student> studentList1=studentList.stream().sorted().collect(Collectors.toList());//自然序列
        log.info("【测试流】自然排序-升序【{}】",JSONObject.toJSONString(studentList1));
         List<Student> studentList2=studentList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());//逆序
         log.info("【测试流】降序【{}】",JSONObject.toJSONString(studentList1));


     }
}
