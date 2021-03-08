package com.zhaolw.zoo.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/22 10:12
 **/
public class TestMap {

    public static void main(String[] args) {
        int MAXIMUM_CAPACITY = 1 << 2;
        System.out.println("" + MAXIMUM_CAPACITY);

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu核" + i);
    }
}
