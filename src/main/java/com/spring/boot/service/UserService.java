package com.spring.boot.service;

import com.spring.boot.common.result.BaseResult;
import com.spring.boot.controller.result.QueryUserListResult;
import com.spring.boot.controller.result.QueryUserResult;
import com.spring.boot.entity.User;
import com.spring.boot.entity.UserExample;

import java.util.List;
import java.util.Map;

/**
 * Created by Ranj on 2017/7/13 0013.
 *
 * 用户操作接口类
 */
public interface UserService {


    User selectByName(String name);

    User selectOneByExample(UserExample example);

    List<User> queryUserList(Map<String, Object> params);

    User selectByPrimaryKey(Integer id);

    BaseResult insert(User record);

    BaseResult updateUser(User record);

    BaseResult deleteByIds(String[] ids);

    /**
     * 查询用户数据（分页查询）
     * @param params
     * @return
     * 方法名应以见文知意至上，不得太随意，如果方法名模棱两可应添加注释
     * 方法名：selectUserByPage
     */
    QueryUserListResult selectUserByPage(Map<String, Object> params);

    QueryUserResult result2(Map<String, Object> params);
}
