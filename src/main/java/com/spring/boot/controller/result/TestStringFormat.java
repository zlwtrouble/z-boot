package com.spring.boot.controller.result;

import java.text.MessageFormat;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/16 16:56
 **/
public class TestStringFormat {
    public static void main(String[] args) {
        String s1 = "商品";
        String s2 = "23";
        String s3 = "44";
        String value = MessageFormat.format("将【{0}】 由 【{1}】改为【{2}】", s1, s2, s3);
        System.out.println(value);
    }
}
