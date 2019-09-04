package com.spring.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/26 11:12
 **/
public class TestSubString {

     public static void main(String[] args) {
       String str="123456789;";
         int length = str.length();
         String substring = str.substring(length - 5, length);
         System.out.println(""+substring);

     }
}
