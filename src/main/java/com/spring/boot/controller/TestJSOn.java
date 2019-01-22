package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/21 10:09
 **/
public class TestJSOn {

     public static void main(String[] args) {
         int []a={10,8,30,2};

         int []b={10,20,30};

         int []c={1,2,3};
         //排序
         //Arrays.sort(a);
         Arrays.sort(a,0,2);
         //Arrays.equals()
         System.out.println(""+ JSON.toJSONString(a));


      }
}
