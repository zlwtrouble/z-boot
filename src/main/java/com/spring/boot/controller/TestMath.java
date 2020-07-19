package com.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMath {

    public static void main(String[] args) {
        float f1 = -1;
        log.info("" + Math.round(f1));
        float f2 = 1;
        log.info("" + Math.round(f2));
        float f3 = 2;
        log.info("" + Math.round(f3));
        float f4 = 2.5f;
        log.info("" + Math.round(f4));
        float f5 = 2.4f;
        log.info("" + Math.round(f5));
    }
}
