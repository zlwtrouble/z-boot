package com.zhaolw.zoo.boot.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/24 12:02
 **/
public class TestSortString {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("As");
        strings.add("sdA");
        strings.add("As");
        strings.add("6");
        strings.add("A");
        strings.add("6");
        strings.add("5");
        strings.add("3");
        strings.add("545");
        strings.add("12");
        strings.add("A");
        strings.add("Asdf");
        strings.add("Asdfasd");
        strings.add("xcvAsdf");
        // Arrays.sort(strings,new Client("1"));


        String join = String.join(",", strings);
        System.out.println("" + join);
        //long l=12345678901234567890L;

        StringBuilder stringBuilder = new StringBuilder("1234567890");
        String trim = stringBuilder.toString().trim();
        trim = trim.substring(0, 10);
        System.out.println(trim);

    }


}
