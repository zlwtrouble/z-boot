package com.spring.boot.controller.thread;

import com.alibaba.fastjson.JSON;
import com.spring.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Slf4j
public class TestJolCore {

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(0);
        //打印对象结构
        Student student = new Student();
        System.out.println(ClassLayout.parseInstance(student).toPrintable());
        Field[] fields = Student.class.getDeclaredFields();
        Field fieldTarget=null;
        for (Field field : fields) {
            if("name".equals(field.getName())){
                fieldTarget=field;
            }
        }
        try {
            fieldTarget.setAccessible(true);
            fieldTarget.set(student,""+98);
        } catch (IllegalAccessException e) {
            log.error("类型异常",e);
        }
        System.out.println("-----------------------------------");

        System.out.println(ClassLayout.parseInstance(student).toPrintable());
        System.out.println(student.getName());
        AtomicReferenceFieldUpdater<Student, Integer> newUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class, Integer.class, "age");

        System.out.println(JSON.toJSONString(student));
    }
}
