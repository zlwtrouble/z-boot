package com.zhaolw.zoo.boot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Test算法 {

    public static void main(String[] args) {
        int[] numbers = new int[3];
        numbers[0] = 3;
        numbers[1] = 32;
        numbers[2] = 31;
        String s = Test算法.PrintMinNumber(numbers);
        System.out.println("结果" + s);
    }


    public static String PrintMinNumber(int[] numbers) {
        String result = "";
        int length = numbers.length;
        if (length < 1) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            list.add(numbers[i]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String result1 = o1 + "" + o2;
                String result2 = o2 + "" + o1;
                return result1.compareTo(result2);
            }
        });
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            result += (iterator.next() + "");
        }
        return result;
    }


}
