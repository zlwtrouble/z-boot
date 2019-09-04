package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.spring.boot.entity.Student;

import java.util.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/19 14:32
 **/
public class TestIterator {

     public static void main(String[] args) {
         List<Student> array = new ArrayList<>();
         Student s1 = new Student();
         s1.setId(1);
         Student s2 = new Student();
         s2.setId(2);
         array.add(s1);
         array.add(s2);

         for (int i = array.size()-1; i >= 0; i--){
             Student temp=array.get(i);
//             if(i==1){
//
//                 continue;
//             }
             System.out.println(i+"反向"+temp.getId());
         }

         Iterator<Student> iterator = array.iterator();
         while (iterator.hasNext()){
             Student next = iterator.next();
             System.out.println(""+next);
             //iterator.remove();
         }
         System.out.println(""+ JSON.toJSONString(array));
         List<Long> list=new ArrayList<>(array.size());
         System.out.println(""+list.toString());
         Map<Long,Long> map =new HashMap<>();
         System.out.println(""+map);
     }



}
