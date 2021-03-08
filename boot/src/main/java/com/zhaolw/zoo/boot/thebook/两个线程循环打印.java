package com.zhaolw.zoo.boot.thebook;

public class 两个线程循环打印 {

    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {


                for (; ; ) {

                    System.out.println(1);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                for (; ; ) {

                    System.out.println(2);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
