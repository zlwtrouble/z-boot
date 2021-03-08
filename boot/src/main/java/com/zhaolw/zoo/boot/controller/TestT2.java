package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型 直接写T表示限制参数的类型，这种方法一般多用于共同操作一个类对象，然后获取里面的集合信息啥的。
 *
 * @author zhaoliwei
 * @description:
 * @date 2019/1/25 13:49
 **/
//默认相当于<T extends Object>。

//通配符泛型不单可以向下限制，如<? extends Collection>，还可以向上限制，如<? super Double>，表示类型只能接受Double及其上层父类类型，如Number、Object类型的实例。
public class TestT2<T> {

    public static void main(String[] args) {
        TestT2<Student> testT = new TestT2<Student>();
        List<Student> array = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        Student s2 = new Student();
        s2.setId(2);
        array.add(s1);
        array.add(s2);
        Student listFisrt = testT.getListFisrt(array);
        System.out.println(listFisrt);
    }


    /**
     * 用法T是一个占位符，用来告诉编译器，这个东西先给我留着，等我编译的时候，告诉你。
     * /**
     * 这个只能传递T类型的数据
     * 返回值 就是Demo<T> 实例化传递的对象类型
     *
     * @param data
     * @return
     */

    private T getListFisrt(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

}
