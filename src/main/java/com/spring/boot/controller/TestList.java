package com.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
public class TestList {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }



     public static void main(String[] args) {
         List<Long> list = new ArrayList<>();
         list.add(154564994156L);
         list.add(254564984156L);
         list.add(354564984156L);
         list.add(454564984156L);
         list.add(55456984156L);
         list.add(654564984156L);
         list.add(75456498455L);
         list.add(854564984156L);
         list.add(954564984156L);
         list.add(1054564984156L);
         list.add(5456498415L);
         list.add(554564984156L);
         list.add(454564984156L);
         list.add(354564984156L);
         list.add(25456498156L);
         list.add(154564984156L);
         list.add(5456498456L);
         list.add(54564984156L);
         list.add(54564984156L);
         list.add(54564984156L);
         list.add(54564984156L);
         list.add(54564984156L);
         list.add(32585555556L);
         list.add(32585555556L);
         list.add(32585555556L);
         list.add(54564984156L);
         list=list.stream().distinct().collect(Collectors.toList());
         System.out.println(""+list);

         List<Long> list2 = new ArrayList<>();
         list2.add(54564984156L);
         list2.add(54564984156L);
         list2.add(54564984156L);
         list2.add(54564984156L);
         list2.add(54564984156L);
         list2.add(32585555556L);
         list2.add(32585555556L);
         list2.add(32585555556L);
         list2.add(54564984156L);
         list2.add(154564994156L);
         list2.add(254564984156L);
         list2.add(354564984156L);
         list2.add(454564984156L);
         list2.add(55456984156L);

         list2.add(554564984156L);
         list2.add(454564984156L);
         list2.add(354564984156L);
         list2.add(654564984156L);
         list2.add(75456498455L);
         list2.add(854564984156L);
         list2.add(954564984156L);
         list2.add(1054564984156L);
         list2.add(5456498415L);
         list2.add(25456498156L);
         list2.add(154564984156L);
         list2.add(5456498456L);

         list2=list2.stream().distinct().collect(Collectors.toList());
         System.out.println(""+list2);

      }
}
