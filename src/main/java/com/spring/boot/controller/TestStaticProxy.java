package com.spring.boot.controller;

import com.spring.boot.api.UserApi;
import com.spring.boot.service.implapi.UserApiImpl;
import com.spring.proxy.CglibProxyFactory;
import com.spring.proxy.ProxyFactory;
import com.spring.proxy.UserDao;

/**
 * @author zhaoliwei
 * @description: 静态代理
 * @date 2019/1/22 16:34
 **/

public class TestStaticProxy {

//    public static void main(String[] args) {
//        //目标对象
//        UserApiImpl target = new UserApiImpl();
//
//        //代理对象,把目标对象传给代理对象,建立代理关系
//        UserProxy proxy = new UserProxy(target);
//
//        proxy.save();//执行的是代理的方法
//    }

    public static void main(String[] args) throws Exception {
        //目标对象
        UserApiImpl target = new UserApiImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        UserApi proxyInstance = (UserApi)proxyFactory.getProxyInstance();

        //UserApi cast = target.getClass().cast(proxyInstance);
        proxyInstance.save();
//        Class<?>[] interfaces = target.getClass().getInterfaces();
//        Class clazz=null ;
//        for(Class c:interfaces){
//            clazz = c;
//        }
//        Object cast = clazz.cast(proxyInstance);

        System.out.println("============cglib=============");
        test();
    }



    public static void test(){
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new CglibProxyFactory(target).getProxyInstance();


        //执行代理对象的方法
        proxy.save();
    }


}
