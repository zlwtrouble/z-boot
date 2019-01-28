package com.spring.proxy;

import com.spring.boot.api.UserApi;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/22 16:18
 **/
public class UserProxy {

    private UserApi target;
    public  UserProxy(UserApi target){
        this.target=target;
    }


    public void save() {
        System.out.println("执行代理方法");
        target.save();
        System.out.println("执行回调方法");
    }
}
