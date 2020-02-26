package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.spring.boot.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2020/2/19 14:55
 **/
public class TestListNull {

//     public static void main(String[] args) {
//         List<String>  stringList=new ArrayList<>();
//         Object[] objs=new Object[1];
//         String str="345345sfasf";
//         stringList.add(str);
//         for (String s : stringList) {
//             System.out.println(""+s);
//             objs[0]=s;
//             s=null;
//             System.out.println(""+s);
//         }
//         System.out.println("=="+str);
//         System.out.println("==="+objs[0]);
//      }


    public static void main(String[] args) {
        List<Student> stringList=new ArrayList<>();
        Object[] objs=new Object[1];
        Student student = new Student();
        student.setName("uesheng");
        stringList.add(student);
        for (Student s : stringList) {
            System.out.println(""+s);
            objs[0]=s;
            s=null;
            System.out.println(""+s);
        }
        System.out.println("=="+ JSON.toJSONString(student));
        System.out.println("==="+JSON.toJSONString(objs[0]));
    }

}
