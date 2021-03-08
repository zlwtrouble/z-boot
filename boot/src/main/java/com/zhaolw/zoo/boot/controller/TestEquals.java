package com.zhaolw.zoo.boot.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/13 11:38
 **/
@Slf4j
public class TestEquals {
    public static void main(String[] args) {

        if ("aa1".equalsIgnoreCase("Aa1")) {
            log.info("【相等1】");
        }
        String a = "aa1方法大V%";
        log.info("转大写" + a.toUpperCase());
        if (a.toUpperCase().equals("AA1")) {
            log.info("【相等2】" + a.toUpperCase());
        }

    }
}
