package com.zhaolw.zoo.boot.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptiblyTest test = new LockInterruptiblyTest();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第一次打断");
        thread1.interrupt();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第二次打断");
        thread1.interrupt();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第三次打断");
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + " ---------- get lock");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程ID：" + Thread.currentThread().getName());
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE) {
                    break;
                }
                //插入数据
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "execute finally");
            lock.unlock();
            System.out.println(thread.getName() + " ---------- release lock");
        }
    }
}

class MyThread extends Thread {
    private LockInterruptiblyTest test = null;

    public MyThread(LockInterruptiblyTest test) {
        this.test = test;
    }

    @Override
    public void run() {

        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "----------interrupt");
        }
    }


}
