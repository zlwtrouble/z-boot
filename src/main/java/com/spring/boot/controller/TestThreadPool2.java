package com.spring.boot.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/2 10:01
 **/
public class TestThreadPool2 {
    /**
     * 核心线程数
     */
    private static Integer corePoolSize = 10;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CountDownLatch c = new CountDownLatch(3);

        System.out.println("开始时间："+start);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2024), new ThreadFactoryBuilder().setNameFormat("线程名-task-%d").build());

        System.out.println("初始化线程池耗时：" + (System.currentTimeMillis() - start));
        TestThreadPool2 testThreadPool2 = new TestThreadPool2();
        long startTime = System.currentTimeMillis();

        String str1 = null;
        String str2 = null;
        try {
            executor.execute(() -> {
                try {
                    testThreadPool2.task1();
                }finally {
                    c.countDown();
                }
                System.out.println("计数器"+c.getCount());
            });
            executor.execute(() -> {
                testThreadPool2.task2();
                c.countDown();
                System.out.println("计数器"+c.getCount());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("第二个任务" + str2 + " " + System.currentTimeMillis());
        System.out.println("第一个任务" + str1 + " " + System.currentTimeMillis());
        try {
            c.await(15,TimeUnit.SECONDS);
            System.out.println("计数器"+c.getCount());
        } catch (Exception e) {
            System.out.println("打断异常");
            Thread.currentThread().interrupt();
        }
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("结束" + runTime + " " + System.currentTimeMillis()+"总时间:"+(System.currentTimeMillis()-start));
    }

    private String task1() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务1线程名：" + Thread.currentThread().getName());
        return "task1";
    }


    private String task2() {
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("任务1线程名：" + Thread.currentThread().getName());
        return "task2";
    }

}
