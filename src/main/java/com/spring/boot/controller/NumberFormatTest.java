package com.spring.boot.controller;

import java.text.NumberFormat;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/17 20:12
 **/
public class NumberFormatTest {

    public static void main(String[] args) {
        NumberFormat nf= NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        System.out.println("格式化后显示数字："+nf.format(10000000));
        System.out.println("格式化后显示数字："+nf.format(10000.345));
        java.math.BigDecimal num1=new java.math.BigDecimal("1.234500");
        System.out.println(num1.stripTrailingZeros().toPlainString() );
    }
}
