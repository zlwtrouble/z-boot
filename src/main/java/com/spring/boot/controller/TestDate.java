package com.spring.boot.controller;

import com.spring.boot.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/15 13:45
 **/
public class TestDate {
     public static void main(String[] args) {
         String time="2019-01-11";
         sys(time);
          time="2019-02-11";
         sys(time);
          time="2019-03-11";
         sys(time);
          time="2019-04-11";
         sys(time);
          time="2019-05-11";
         sys(time);
          time="2019-06-11";
         sys(time);
          time="2019-07-11";
         sys(time);
          time="2019-08-11";
         sys(time);
          time="2019-09-11";
         sys(time);
         time="2019-10-11";
         sys(time);
         time="2019-11-11";
         sys(time);
         time="2019-12-11";
         sys(time);
     }

    private static void sys(String time) {
        Date date = DateUtil.parse(time, DateUtil.YYMMDD);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dateNowStr = sdf.format(date);
        System.out.println("格式化后的日期：" + dateNowStr);
    }
}
