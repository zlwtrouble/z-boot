package com.zhaolw.zoo.boot.controller;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/17 18:53
 **/
public class TestClass {

    public static void main(String[] args) throws Exception {

        Class<?> classa = Class.forName("com.zhaolw.zoo.boot.entity.Student");
        Class<? extends Class> aClass = classa.getClass();
        //Object a = Class.forName("com.spring.boot.entity.Student").newInstance();
        System.out.println("");
        String name = BigDecimal.class.getName();
        System.out.println(name);
        System.out.println(name);
    }
}
