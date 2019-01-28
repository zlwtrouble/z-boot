package com.spring.boot.controller;

import com.spring.boot.common.result.BaseResult;
import com.spring.boot.controller.form.QueryUserForm;
import com.spring.boot.controller.form.UserForm;
import com.spring.boot.controller.result.QueryUserListResult;
import com.spring.boot.controller.result.QueryUserResult;
import com.spring.boot.entity.User;
import com.spring.boot.service.UserService;
import com.spring.boot.vo.BaseResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ranj on 2017/7/13 0013.
 * 用户操作层
 */
@RestController
@RequestMapping("/user")
public class UserContoller {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    public String user() {
        return "/user";
    }

    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo queryUserList(QueryUserForm form) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", form.getPage());
        params.put("rows", form.getRows());
        params.put("name", form.getUsername());
        log.info("查询用户入参【form:{}】", form);
        QueryUserListResult result = userService.selectUserByPage(params);
        log.info("【查询结果出参：result{}】", result);
        return new BaseResultVo(result);
    }

    /**
     * 新添 
     *
     * @param request
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo addUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String realName = request.getParameter("realName");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setRealName(realName);
        user.setPassword(password);
        user.setRowAddTime(new Date());
        BaseResult result = userService.insert(user);
        return new BaseResultVo(result);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo updateLearn(UserForm form) {
        User user = userService.selectByPrimaryKey(form.getId());
        BeanUtils.copyProperties(form, user);
        BaseResult result = userService.updateUser(user);
        return new BaseResultVo(result);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo deleteUser(String ids) {
        //删除操作
        BaseResult result = userService.deleteByIds(ids.split(","));
        return new BaseResultVo(result);
    }

    /**
     * 查询用户信息（测试）
     * @param form
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public BaseResultVo query(QueryUserForm form) {
        /* 分页 */
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", form.getPage());
        params.put("rows", form.getRows());
        //params.put("name", form.getUsername());
        log.info("查询用户入参【form:{}】", form);
        //QueryUserListResult result = userService.selectUserByPage(params);
        /* 不分页 */
        QueryUserResult result = userService.result2(params);
        log.info("【查询结果出参：{}】", result);
        return new BaseResultVo(result);
    }


}


