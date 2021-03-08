package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.entity.Student;

import java.util.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2020/3/10 11:28
 **/
public class TestTreemap {


    public static void main(String[] args) {
        Map<Integer, Student> studentMap = new TreeMap<>(Comparator.reverseOrder());

        Student student1 = new Student();
        student1.setId(5);
        student1.setName("55");
        studentMap.put(student1.getId(), student1);

        Student student2 = new Student();
        student2.setId(1);
        student2.setName("11");
        studentMap.put(student2.getId(), student2);

        Student student3 = new Student();
        student3.setId(7);
        student3.setName("77");
        studentMap.put(student3.getId(), student3);

        Student student4 = new Student();
        student4.setId(99);
        student4.setName("99");
        studentMap.put(student4.getId(), student4);

        List<Student> list = new ArrayList<>(studentMap.values());

        for (Student student : list) {
            System.out.println("" + student.getName());

        }

    }
}
