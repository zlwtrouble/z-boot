package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/25 09:17
 **/
public class TestT3 {

     public static void main(String[] args) {
         TestT3 testT3 = new TestT3();
         List<Long> list =new ArrayList<>();
         list.add(55L);
         Long aLong = testT3.addLL(list);
         System.out.println(""+ JSON.toJSONString(aLong));
     }

      private <T> T addLL(List<T> tList){

          return tList.get(0);
      }
}
