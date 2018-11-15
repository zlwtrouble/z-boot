package com.spring.boot.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/9/13 10:33
 **/
public class TestMin {

    public static void main(String[] args) {
       long min=31;
       if(min>5){
           System.out.println("true");
       }else{
           System.out.println("false");
       }
        Date beforeMinute = getBeforeMinute(5);
        Date beforeMinute1 = getBeforeMinute(11);
        System.out.println(""+beforeMinute);
        System.out.println(""+beforeMinute1);
        if(beforeMinute.after(beforeMinute1)){
            System.out.println("true");
        }


        int i = new BigDecimal("0.01").compareTo(BigDecimal.ZERO);
        System.out.println(""+i);

    }



    protected static String getLastMonth(int num) {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(num);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }

    private static Date getBeforeMinute(int Minute) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - Minute);
        return now.getTime();
    }


}
