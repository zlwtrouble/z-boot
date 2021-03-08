package com.zhaolw.zoo.boot.controller;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/22 08:58
 **/
public class TestInt {


    public static void main(String[] args) {


        System.out.println(9 / 2 + 1);
        System.out.println(8 / 2 + 1);
        System.out.println(7 / 2 + 1);
        System.out.println(6 / 2 + 1);
        System.out.println(5 / 2 + 1);
        System.out.println(4 / 2 + 1);
        System.out.println(3 / 2 + 1);
        System.out.println(2 / 2 + 1);
        System.out.println(1 / 2 + 1);

        BigDecimal bigDecimal = new BigDecimal("999");
        BigDecimal bigDecimal2 = new BigDecimal("999");
        BigDecimal bigDecimal3 = new BigDecimal("1000");
        BigDecimal bigDecimal4 = new BigDecimal("998");

        System.out.println("等于" + bigDecimal2.compareTo(bigDecimal));
        System.out.println("大于" + bigDecimal3.compareTo(bigDecimal));
        System.out.println("小于" + bigDecimal4.compareTo(bigDecimal));
    }


    private int ssdfa(Integer x) {
        int i;
        i = x;
        return i;
    }

}
