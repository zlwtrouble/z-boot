package com.zhaolw.zoo.boot.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/2 10:01
 **/
public class TestThreadPool {
    /**
     * 核心线程数
     */
    private static Integer corePoolSize = 10;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2024), new ThreadFactoryBuilder().setNameFormat("线程名-task-%d").build());

        System.out.println("初始化线程池耗时：" + (System.currentTimeMillis() - start));

        TestThreadPool testThreadPool = new TestThreadPool();
        long startTime = System.currentTimeMillis();
        //定义callable任务
        Callable<String> callable1 = testThreadPool::task1;
        Callable<String> callable2 = testThreadPool::task2;
        //定义futrueTask
        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);
        executor.submit(futureTask1);
        executor.submit(futureTask2);
        String str1 = null;
        String str2 = null;
        try {
            str1 = futureTask1.get();
            str2 = futureTask2.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("第二个任务" + str2 + " " + System.currentTimeMillis());
        System.out.println("第一个任务" + str1 + " " + System.currentTimeMillis());

        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("结束" + runTime + " " + System.currentTimeMillis() + "总时间:" + (System.currentTimeMillis() - start));
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
