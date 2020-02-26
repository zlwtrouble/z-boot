package com.spring.boot.controller;

import com.spring.boot.common.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class TestCal {

     public static void main(String[] args) {
         TestCal testCal = new TestCal();
         testCal.checkTime(DateUtil.parse("2018-12-01 00:00:00"));
     }


    private void checkTime(Date time) {
        if (time == null) {
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        boolean flag = false;
        if (hour == 23 && minute == 59 && second == 59) {
            flag = true;
        }
        if (hour == 0 && minute == 0 && second == 0) {
            flag = true;
        }
        if (hour == 0 && minute == 0 && second == 1) {
            flag = true;
        }
          System.out.println("结果"+flag);
    }
}
