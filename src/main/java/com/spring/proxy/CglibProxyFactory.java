package com.spring.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/23 14:06
 **/
public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public  CglibProxyFactory(Object target){
        this.target=target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en =new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类（代理对象）
        return en.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib开始");
        method.invoke(target,objects);
        System.out.println("cglib结束");

        return null;
    }
}
