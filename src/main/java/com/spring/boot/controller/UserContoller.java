package com.spring.boot.controller;

import com.spring.boot.controller.form.QueryUserForm;
import com.spring.boot.entity.User;
import com.spring.boot.vo.BaseResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    @RequestMapping("/page")
    public String user() {
        return "/user";
    }

    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
    public BaseResultVo queryUserList(QueryUserForm form) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", form.getPage());
        params.put("rows", form.getRows());
        params.put("name", form.getUsername());
        return new BaseResultVo(params);
    }

    /**
     * 新添
     *
     * @param request
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResultVo addUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String realName = request.getParameter("realName");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setRealName(realName);
        user.setPassword(password);
        user.setRowAddTime(new Date());
        return new BaseResultVo(user);
    }
}



