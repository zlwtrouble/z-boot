package com.spring.boot.controller.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhaoliwei
 */
@Slf4j
public class TestReentrantLock {

    public static void main(String[] args) {

        TestReentrantLock testReentrantLock = new TestReentrantLock();
    testReentrantLock.test1();
    }

    public void test1() {


        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("start thread{}", current.getName());
                for (; ; ) {
                    log.info("ready park:{}", current.getName());
                    LockSupport.park();
                    log.info("Already awakened{}", current.getName());
                }
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("start thread{}", current.getName());
                for (; ; ) {
                    log.info("ready park:{}", current.getName());
                    LockSupport.park();
                    log.info("Already awakened{}", current.getName());
                }
            }
        });

        t0.start();
        t1.start();


        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t0);


    }


}
