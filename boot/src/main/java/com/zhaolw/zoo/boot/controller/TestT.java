package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.entity.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 测试泛型
 *
 * @author zhaoliwei
 * @description:
 * @date 2019/1/25 13:49
 **/
public class TestT {

    public static void main(String[] args) {
        TestT testT = new TestT();
        List<Student> array = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        Student s2 = new Student();
        s2.setId(2);
        array.add(s1);
        array.add(s2);
        Student listFisrt = testT.getListFisrt(array);
        System.out.println(listFisrt);
        testT.f("Str");
    }


    /**
     * 用法T是一个占位符，用来告诉编译器，这个东西先给我留着，等我编译的时候，告诉你。
     * 这个<T> T 可以传入任何类型的List
     * 参数T
     * 第一个 表示是泛型
     * 第二个 表示返回的是T类型的数据
     * 第三个 限制参数类型为T
     *
     * @param data
     * @return
     */
    private <T> T getListFisrt(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    /*
    泛型操作集合和对象
     */
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // Correct
        }
    }


}
