package com.spring.boot.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/14 10:06
 **/
public class TestThreadPool3 {

    private static ExecutorService es = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2042));

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            es.execute(() -> {
                System.out.println(""+ finalI);
                try {

                    ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);
                    System.out.println("当前排队线程数：" + tpe.getQueue().size());
                    System.out.println("当前活动线程数：" + tpe.getActiveCount());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);


            System.out.println("================结束=================");

            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成线程数：" + completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数：" + taskCount);

            Thread.sleep(3000);


        //ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);

       /* while (true) {
            System.out.println();

            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成线程数：" + completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数：" + taskCount);

            Thread.sleep(3000);
        }*/

    }


}
