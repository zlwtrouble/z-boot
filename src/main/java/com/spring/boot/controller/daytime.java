package com.spring.boot.controller;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/19 14:01
 **/
public class daytime {

    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), 0, 0, 0);
        Date time = calendar.getTime();


        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), 23, 59, 59);
        Date time1 = calendar.getTime();
        System.out.println("前一天的起始时间是：" + time);
        System.out.println("前一天的结束时间是：" + time1);
    }
}
