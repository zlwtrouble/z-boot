package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import com.zhaolw.zoo.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/15 09:47
 **/
@Slf4j
public class TestStringBuildToString {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        System.out.println("结果" + sb.toString());

        Student student = null;
        if (student == null) {
            student = new Student();
        }

        Student student2 = new Student();

        student2.setId(student.getId());

        JSON.toJSONString(student2);

        List<Student> list = new ArrayList<>();

        for (Student s : list) {
            System.out.println("" + s.getId());
        }


    }
}
