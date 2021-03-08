package com.zhaolw.zoo.boot.controller;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/28 15:30
 **/
public class TestTimeUnit {
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("1");
        }
    }
}
