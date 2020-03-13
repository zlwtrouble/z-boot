package com.spring.boot.controller;

import com.spring.boot.entity.Student;

/**
 * @author zhaoliwei
 * @description:
 * @date 2020/2/27 16:58
 **/
public class TestListSapce {

     public static void main(String[] args) {
         Runtime runtime = Runtime.getRuntime();
         runtime.gc();
         try {
             Thread.sleep(5000);
         } catch (Exception ee) {
             ee.printStackTrace();
         }
         long startMem = runtime.freeMemory();
         Student o = new Student();
         o.setName("1234567890123456789012345678901234567890");
         Student o2 = new Student();
         o2.setName("11234567890123456789012345678901234567890");
         long orz = runtime.freeMemory();
         System.out.println("内存差："+(startMem-orz));
         System.out.println(o);
         System.out.println(o2);
      }
}
