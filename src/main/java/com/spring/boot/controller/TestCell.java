package com.spring.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/25 09:30
 **/
public class TestCell {

     public static void main(String[] args) {
         int a=5;
         int day=0;
       Integer c= (int)Math.ceil((0D+a)/day);

         Integer d=  (int)Math.floor((0D+a)/day);
         System.out.println("c===>"+c);
         System.out.println("d===>"+d);
      }
}
