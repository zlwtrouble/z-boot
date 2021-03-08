package com.zhaolw.zoo.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/4/30 16:06
 **/
@Slf4j
public class TestNewData {


    public static void main(String[] args) {
        Date date = new Date();
        log.info("" + date.getTime());
    }
}
