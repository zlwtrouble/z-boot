package com.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;

@Slf4j
public class TestInteger {


    public static void main(String[] args) {
        Integer integer = 5;
        Object obj1 = integer;
        int i = 5;
        Object obj2 = i;

        double i3 = 5;
        Object obj3 = i3;
        if (obj1 instanceof Integer) {
            log.info("类型匹配1" + Double.parseDouble(new BigDecimal("1").stripTrailingZeros().toPlainString()));
        }

        if (obj2 instanceof Integer) {
            log.info("类型匹配2");
        }

        if (obj3 instanceof Integer) {
            log.info("类型匹配3");
        }

        if (obj3 instanceof Double) {
            log.info("类型匹配4");
        }

        boolean flag1 = true;
        Object obj4 = flag1;
        if (obj4 instanceof Boolean) {
            log.info("类型匹配5" + obj4.toString());
        }

    }
}
