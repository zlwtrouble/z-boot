package com.zhaolw.zoo.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/26 11:12
 **/
public class TestSubString {

    public static void main(String[] args) {
        String str = "12345678";
        if (str.length() > 8) {
            System.out.println(str.substring(0, 8));
        }

    }
}
