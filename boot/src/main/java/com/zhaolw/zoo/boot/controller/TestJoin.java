package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TestJoin {


    public static void main(String[] args) {
        Student student = new Student();
        student.setName("1");

        Student student2 = new Student();

        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student2);

        log.info(list.stream().map(Student::getName)
                .collect(Collectors.joining("、")));


    }
}
