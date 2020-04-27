package com.spring.boot.controller;

import com.spring.boot.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class TestCalender {


    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        Date parse = DateUtil.parse("2020-04-01", DateUtil.YYMMDD);
        c.setTime(parse);
        log.info("看看" + DateUtil.formatFully(c.getTime()));
        int day = 9;
        int hour = 0;
        if (hour == 24) {
            //设置天
            c.set(Calendar.DAY_OF_MONTH, day);
            //将小时至23
            c.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至59
            c.set(Calendar.MINUTE, 59);
            //将秒至59
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.MILLISECOND, 0);
        } else {
            //设置天
            c.set(Calendar.DAY_OF_MONTH, day);
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }

        log.info("再看看" + DateUtil.formatFully(c.getTime()));
    }
}
