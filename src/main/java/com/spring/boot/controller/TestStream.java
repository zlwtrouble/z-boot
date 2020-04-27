package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.spring.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/22 11:06
 **/
@Slf4j
public class TestStream {
//     public static void main(String[] args) {
//         List<Student> studentList= Arrays.asList(new Student(1,"ziwen1",10),new Student(2,"aiwen2",18),new Student(3,"biwen3",28));
//         log.info("【测试流】原始【{}】", JSONObject.toJSONString(studentList));
//         //自然序列
//         List<Student> studentList1=studentList.stream().sorted().collect(Collectors.toList());
//        log.info("【测试流】自然排序-升序【{}】",JSONObject.toJSONString(studentList1));
//         //逆序
//         List<Student> studentList2=studentList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//         log.info("【测试流】降序【{}】",JSONObject.toJSONString(studentList2));
//
//         List<Student> studentList3= Arrays.asList(new Student(1,"ziwen1",10),new Student(1,"aiwen2",18),new Student(1,"biwen3",28),new Student(4,"asdfbiwen3",28),new Student(5,"asdfdsbiwen3",28),new Student(null,"asdfdsbiwen3",28));
//
//         studentList3.forEach(o-> {
//             System.out.println("流foreach"+o);
//         });
//
//         //list转map  用流key不能重复
//         List<Long> listForeach= Lists.newArrayList();
//         Map<Integer, Student> collect = studentList3.stream().collect(Collectors.toMap(Student::getId, student -> student,(k1,k2)->k1));
//         System.out.println(collect);
//
//         studentList3.forEach(o->{
//             System.out.println("普通流"+o);
//         });
//
//         studentList3.parallelStream().forEach(o->{
//             System.out.println("并行流"+o);
//         });
//         Integer one=1;
//
//         List<Student> collectFilter = studentList3.parallelStream().filter(o->one.equals(o.getId())).collect(Collectors.toList());
//
//         System.out.println("并行过滤流"+collectFilter);
//
//
//         Map<Integer, Student> collect11 = studentList3.parallelStream().collect(Collectors.toMap(Student::getId, student -> student,(k1,k2)->k1));
//         System.out.println("流"+collect11);
//
//
//        //流转list 并去重
//         List<Integer> productIds=null;
//             productIds = studentList3.stream().map(Student::getId).distinct()
//                     .collect(Collectors.toList());
//         System.out.println("去重"+productIds);
//     }


    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(new Student(1, "ziwen1", 56), new Student(2, "aiwen2", null), new Student(3, "biwen3", 28), new Student(4, "biwen31", null));
//        log.info("【测试流】原始【{}】", JSONObject.toJSONString(studentList));
//        //自然序列
//        List<Student> studentList1=studentList.stream().sorted().collect(Collectors.toList());
//        log.info("【测试流】自然排序-升序【{}】",JSONObject.toJSONString(studentList1));
//        //逆序
//        List<Student> studentList2=studentList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//        log.info("【测试流】降序【{}】",JSONObject.toJSONString(studentList2));

//        log.info("流去重前"+ JSON.toJSONString(studentList));
//        List<Student> collect = studentList.stream().distinct().collect(Collectors.toList());
//        log.info("流去重后"+ JSON.toJSONString(collect));


        //     studentList.stream().map(Student::new).collect(Collectors.toList());


        List<Student> collect1 = studentList.stream().sorted(Comparator.comparing(Student::getAge, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
        log.info("流排序" + JSON.toJSONString(collect1));
    }
}
