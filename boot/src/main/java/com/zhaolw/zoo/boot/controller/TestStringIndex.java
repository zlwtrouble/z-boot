package com.zhaolw.zoo.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/9/13 10:33
 **/
public class TestStringIndex {

    public static void main(String[] args) {

        String s = "33_156489165156";

        int i = s.indexOf("_");
        System.out.println(Long.valueOf(s.substring(i + 1)));

    }

}
