package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.common.utils.DateUtil;

import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/15 13:45
 **/
public class TestDate2 {
    public static void main(String[] args) {
        Date parse = DateUtil.parse("2020-10-29 17:23:53", DateUtil.YYMMDD_HHmmSS);
        long l = (System.currentTimeMillis() - parse.getTime()) / 1000;
        System.out.println(l);
    }


}
