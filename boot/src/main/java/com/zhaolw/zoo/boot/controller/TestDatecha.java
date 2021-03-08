package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.common.utils.DateUtil;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/15 13:45
 **/
public class TestDatecha {
    public static void main(String[] args) {
        long l = DateUtil.parse("2020-08-20 14:17:22", DateUtil.YYMMDD_HHmmSS).getTime() - DateUtil.parse("2020-08-22 14:33:30", DateUtil.YYMMDD_HHmmSS).getTime();
        System.out.println("" + (l / 1000));
    }


}
