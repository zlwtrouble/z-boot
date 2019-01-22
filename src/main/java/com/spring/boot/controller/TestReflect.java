package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.spring.boot.annotation.FieldNameAnnotation;
import com.spring.boot.common.utils.FieldValueCompareUtil;
import com.spring.boot.entity.Student;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/18 11:24
 **/
public class TestReflect {

    public static void main(String[] args) throws Exception {

        Student s1 = new Student();

        //s1.setName("张三丰");
        //s1.setBirthdate(new Date());
        //s1.setWallet(new BigDecimal("102.30"));

        Student s2 = new Student();

        s2.setName("张五丰");
        s2.setBirthdate(new Date());
        s2.setWallet(new BigDecimal("102.20"));
        List<String> strings = FieldValueCompareUtil.compareValueContentByObject(s1, s2, null, null);
        System.out.println("第一个" + JSON.toJSONString(strings));

        Class<? extends Student> aClass = s2.getClass();

        // aClass.
        Field fieldName = aClass.getDeclaredField("name");
        System.out.println("aClass:" + JSON.toJSONString(aClass));
        Field[] declaredFields = aClass.getDeclaredFields();
        System.out.println("Field[] :" + JSON.toJSONString(declaredFields));
        boolean annotationPresent = fieldName.isAnnotationPresent(FieldNameAnnotation.class);
        System.out.println("annotationPresent :" + JSON.toJSONString(annotationPresent));
        fieldName = null;
//         fieldName.isAnnotationPresent(FieldNameAnnotation.class);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        String name = list.get(0).getClass().getName();
        System.out.println("name:"+name);


    }
}
