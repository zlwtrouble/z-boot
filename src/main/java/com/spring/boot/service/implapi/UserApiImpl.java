package com.spring.boot.service.implapi;

import com.spring.boot.api.UserApi;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/22 16:16
 **/
public class UserApiImpl implements UserApi {
    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
