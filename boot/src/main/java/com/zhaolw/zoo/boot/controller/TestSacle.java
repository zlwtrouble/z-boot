package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import com.zhaolw.zoo.boot.entity.Student;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/13 09:46
 **/
public class TestSacle {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.256396");

        BigDecimal bigDecimal2 = new BigDecimal("0.2563962");

        Student student = new Student();
        BigDecimal multiply = bigDecimal.multiply(bigDecimal2);
        student.setWallet(multiply.setScale(6, BigDecimal.ROUND_DOWN));
        System.out.println("" + JSON.toJSONString(student));


        BigDecimal bigDecima3 = new BigDecimal("20.632");

        BigDecimal bigDecimal4 = new BigDecimal("337");

        BigDecimal divide = bigDecima3.divide(bigDecimal4, 6, BigDecimal.ROUND_DOWN);
        System.out.println("" + divide);

        BigDecimal multiply1 = divide.multiply(bigDecimal4);
        System.out.println("" + multiply1);

    }


}
