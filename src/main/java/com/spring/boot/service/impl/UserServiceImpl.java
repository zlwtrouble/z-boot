package com.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.common.enums.Status;
import com.spring.boot.common.result.BaseResult;
import com.spring.boot.controller.result.QueryUserListResult;
import com.spring.boot.controller.result.QueryUserResult;
import com.spring.boot.dao.UserMapper;
import com.spring.boot.entity.User;
import com.spring.boot.entity.UserExample;
import com.spring.boot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * Created by Ranj on 2017/7/25 0025.
 *
 * 用户接口实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectByName(String name) {
        User user = userMapper.selectByName(name);
        return user;
    }

    @Override
    public User selectOneByExample(UserExample example) {
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> queryUserList(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return userMapper.queryUserList(params);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResult insert(User record) {
        BaseResult result = new BaseResult();
        if (StringUtils.isBlank(record.getName())){
            result.setStatus(Status.PARAMETER_NOT_EXIST);
            result.setDescription("帐号不能为空");
            return result;
        }
        if (StringUtils.isBlank(record.getRealName())){
            result.setStatus(Status.PARAMETER_NOT_EXIST);
            result.setDescription("用户名不能为空");
            return result;
        }
        if (StringUtils.isBlank(record.getPassword())){
            result.setStatus(Status.PARAMETER_NOT_EXIST);
            result.setDescription("密码不能为空");
            return result;
        }
        userMapper.insert(record);
        result.setStatus(Status.SUCCESS);
        return result;
    }

    @Override
    public BaseResult updateUser(User record) {
        BaseResult result = new BaseResult();
        if (StringUtils.isBlank(record.getName())){
            result.setStatus(Status.PARAMETER_NOT_EXIST);
            result.setDescription("帐号不能为空");
            return result;
        }
        if (StringUtils.isBlank(record.getRealName())){
            result.setStatus(Status.PARAMETER_NOT_EXIST);
            result.setDescription("用户名不能为空");
            return result;
        }
        if (StringUtils.isBlank(record.getPassword())){
            result.setStatus(Status.PARAMETER_NOT_EXIST);
            result.setDescription("密码不能为空");
            return result;
        }
        userMapper.updateByPrimaryKeySelective(record);
        result.setStatus(Status.SUCCESS);
        return result;
    }

    @Override
    public BaseResult deleteByIds(String[] ids) {
        BaseResult result = new BaseResult();
        try {
            for (String id : ids) {
                userMapper.deleteByPrimaryKey(Integer.valueOf(id));
            }
            result.setStatus(Status.SUCCESS);
        }catch (Exception ex){
            result.setStatus(Status.FAIL);
        }
        return result;
    }

    @Override
    public QueryUserListResult selectUserByPage(Map<String, Object> params) {
        QueryUserListResult result = new QueryUserListResult();
        PageHelper.startPage((Integer) params.get("page"), (Integer) params.get("rows"));
        List<User> list = userMapper.queryUserList(params);
        PageInfo info = new PageInfo(list);
        result.setPaginator(info);
        result.setList(list);
        result.SUCCESS();
        return result;
    }

    @Override
    public QueryUserResult result2(Map<String, Object> params) {
        QueryUserResult result = new QueryUserResult();
        try {
            PageHelper.startPage(1, 10);
            List<User> list = userMapper.queryUserList(params);
            result.SUCCESS();
            result.setUserList(list);
        }catch (NullPointerException ex){
            // 自定义返回（不推荐）
            result.setCode("NULL_RESULT");
            result.setDescription("系统异常");
            // 返回枚举（推荐）
            result.setStatus(Status.SYSTEM_EXCEPTION);
        }catch (Exception ex){
            result.setStatus(Status.FAIL);
            result.setDescription(ex.getMessage());
        }
        return result;
    }
}
