package com.spring.boot.controller;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/1 10:05
 **/
public class TestCompare {
     public static void main(String[] args) {
         BigDecimal a = new BigDecimal("0");
         BigDecimal b = new BigDecimal("2");
         BigDecimal c = new BigDecimal("-1");
         BigDecimal d = new BigDecimal("0");
         BigDecimal e = new BigDecimal("-3");

         System.out.println(">"+a.compareTo(BigDecimal.ZERO));
         System.out.println(""+BigDecimal.ZERO.compareTo(e));
         System.out.println(a.compareTo(BigDecimal.ZERO)==0);
         System.out.println("--------------------");


         Integer x = 5;
         //大于1
         System.out.println(x.compareTo(3));
         //等于0
         System.out.println(x.compareTo(5));
         //小于-1
         System.out.println(x.compareTo(8));

         if (BigDecimal.ZERO.compareTo(c) >= 0) {
             System.out.println("数量必须大于0");
         }

     }
}
