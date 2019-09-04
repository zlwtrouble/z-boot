package com.spring.boot.controller;

import com.spring.boot.common.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/19 16:12
 **/
public class TestCal {

     public static void main(String[] args) {
         Calendar calendar = Calendar.getInstance();
     //  /  calendar.setTime(DateUtil.parse("2019-08-17 01:59:21",DateUtil.YYMMDD_HHmmSS));
         calendar.set(Calendar.MILLISECOND, 0);
         calendar.add(Calendar.DAY_OF_MONTH, -1);
         calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
System.out.println(calendar.getTime().getTime());
System.out.println(DateUtil.format(calendar.getTime(),null));
         calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 59, 59);
         calendar.set(Calendar.MILLISECOND, 999);
         Date time = calendar.getTime();
         try {
             Thread.sleep(2000L);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println(calendar.getTime().getTime());
         System.out.println(   DateUtil.format(time,null));

         Date parse = DateUtil.parse("2019-08-18 23:59:59", DateUtil.YYMMDD_HHmmSS);
         System.out.println(""+parse);
     }
}
