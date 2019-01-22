package com.spring.boot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/15 13:45
 **/
public class TestDate {
     public static void main(String[] args) {
         Date date = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
         String dateNowStr = sdf.format(date);
         System.out.println("格式化后的日期：" + dateNowStr);
     }
}
