package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/14 11:25
 **/
public class TestSet {


     public static void main(String[] args) {
        List<Long> ids=new ArrayList<>();
        // ids.add(9L);
        // ids.add(null);
         //ids.add(5L);
         //ids.add(null);
         //ids.add(5L);
         //ids.add(null);
        // ids.add(1L);
        // ids.add(5L);
         ids.add(null);
         ids.add(null);
         ids.add(null);
         ids.add(null);
         ids.add(null);
         //ids.add(8L);
         Set<Long>  idSet =new LinkedHashSet<>();
         for (Long id : ids) {
             if(id!=null){
                 idSet.add(id);
             }
         }

         ArrayList<Long> longs = new ArrayList<>(idSet);
         System.out.println(""+ JSON.toJSONString(longs));
     }
}
