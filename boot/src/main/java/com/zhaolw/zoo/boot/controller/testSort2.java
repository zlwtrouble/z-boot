package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import com.zhaolw.zoo.boot.common.utils.StringUtil;
import com.zhaolw.zoo.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2020/1/3 15:52
 **/
@Slf4j
public class testSort2 {

    public static void main(String[] args) {
        List<Student> result = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("DLS1");
        result.add(student1);

        Student student2 = new Student();
        student2.setName("DLS10");
        result.add(student2);


        Student student3 = new Student();
        student3.setName("DLS20");
        result.add(student3);

        Student student4 = new Student();
        student4.setName("DLS3");
        result.add(student4);

        Student student5 = new Student();
        student5.setName("DLS");
        result.add(student5);

        result.sort(Comparator.comparing(Student::getName).reversed());

        log.info(JSON.toJSONString(result));
        result.sort((o1, o2) -> {
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            String str1 = StringUtil.getNumber(o1.getName());
            String str2 = StringUtil.getNumber(o2.getName());
            Integer num1 = Integer.valueOf(StringUtil.isBlank(str1) ? "0" : str1);

            Integer num2 = Integer.valueOf(StringUtil.isBlank(str2) ? "0" : str2);
            //升序 null放最后  降序加负号
            return -Integer.compare(num1.compareTo(num2), 0);
        });

        log.info(JSON.toJSONString(result));

    }
}
