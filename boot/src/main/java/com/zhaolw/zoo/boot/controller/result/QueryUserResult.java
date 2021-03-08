package com.zhaolw.zoo.boot.controller.result;

import com.google.common.collect.Lists;
import com.zhaolw.zoo.boot.common.result.BaseResult;
import com.zhaolw.zoo.boot.entity.User;

import java.util.List;

/**
 * Created by Ranj on 2017/7/20 0020.
 */
public class QueryUserResult extends BaseResult {
    List<User> userList = Lists.newArrayList();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
