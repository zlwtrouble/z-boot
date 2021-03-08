package com.zhaolw.zoo.boot.thebook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 三个线程循环打印 {

    Lock lock = new ReentrantLock();

    int num = 1;

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public static void main(String[] args) {
        三个线程循环打印 三个线程循环打印 = new 三个线程循环打印();
        三个线程循环打印.method();
    }

    public void method() {


        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (num != 1) {
                        condition1.await();
                    }
                    System.out.println(1);
                    num = 2;
                    condition2.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (num != 2) {
                        condition2.await();
                    }
                    System.out.println(2);
                    num = 3;
                    condition3.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (num != 3) {
                        condition3.await();
                    }

                    System.out.println(3);
                    num = 1;
                    condition1.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }).start();


        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Thread(()->{
//            System.out.println(2);
//        }).start();
    }

}
