package com.spring.boot.controller;

import com.spring.boot.entity.Student;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/29 10:28
 **/
public class TestEmpty {

    static int i;

    private ReentrantLock lock;
    private Condition notEmpty;
    private Condition empty;

    private Student[] students = new Student[10];

    private Integer count = 0;

    public static void main(String[] args) {


        TestEmpty testEmpty = new TestEmpty();

        testEmpty.testPool(testEmpty);
        return;


    }

    private void testPool(TestEmpty testEmpty) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024
        ), new ThreadPoolExecutor.CallerRunsPolicy());


        init(testEmpty);

        Student student1 = new Student();
        student1.setId(1);

        students[count] = student1;
        count++;

        Student student2 = new Student();
        student2.setId(1);

        students[count] = student2;
        count++;
        System.out.println(Thread.currentThread().getName() + "主线程" + i);
        while (i < 600) {
            System.out.println(Thread.currentThread().getName() + "准备开始" + i);
            poolExecutor.execute(() -> {

                lock.lock();

                System.out.println(Thread.currentThread().getName() + " " + i);
                i++;

                if(i==500){
                    this.cleanI();
                }

                if (i >= 500) {
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                lock.unlock();
            });
        }
        System.out.println(Thread.currentThread().getName() + "主线程" + i);
    }

    private void cleanI() {
        while (true) {
          i--;
            if (i < 300) {
                System.out.println(Thread.currentThread().getName() + "减少到唤醒" + i);
                empty.signalAll();
                return;
            }
        }
    }

    private static void init(TestEmpty testEmpty) {
        testEmpty.lock = new ReentrantLock(true);
        testEmpty.notEmpty = testEmpty.lock.newCondition();
        testEmpty.empty = testEmpty.lock.newCondition();
    }

}
