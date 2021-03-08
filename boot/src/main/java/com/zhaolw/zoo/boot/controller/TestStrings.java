package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import com.zhaolw.zoo.boot.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/22 08:58
 **/
public class TestStrings {


    public static void main(String[] args) throws IllegalAccessException {


        Student student = new Student();
        student.setAge(1);
        Class<? extends Student> aClass = student.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            TestStrings.makeAccessible(field);
            field.set(student, "asdf");
        }

        System.out.println("" + JSON.toJSONString(student));


    }

    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
                .isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }


    private int ssdfa(Integer x) {
        int i;
        i = x;
        return i;
    }

}
