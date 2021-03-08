package com.zhaolw.zoo.boot.service.implapi;

import com.zhaolw.zoo.boot.api.UserApi;

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
