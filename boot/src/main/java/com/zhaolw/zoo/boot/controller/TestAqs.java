package com.zhaolw.zoo.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoliwei
 * @description:
 * @date 2020/1/7 11:07
 **/
@Slf4j
public class TestAqs {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
//        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
//                    semaphore.acquire(); // 获取一个许可
                    test(threadNum);
//                    semaphore.release(); // 释放一个许可
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        log.info("等待");
        countDownLatch.await(10, TimeUnit.SECONDS);
        log.info("完成");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{}", threadNum);
    }


}
