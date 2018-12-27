package com.spring.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/20 09:55
 **/
public class TestSql {
     public static void main(String[] args) {
         String str="asdf''as=df".replaceAll("(--)+|(=)+", "");
         System.out.println(""+str);
      }
}
