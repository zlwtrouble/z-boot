package com.spring.boot.controller;

import com.spring.boot.common.enums.ResultEnum;
import com.spring.boot.entity.User;
import com.spring.boot.entity.UserExample;
import com.spring.boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录器
 * Created by Ranj on 2017/7/13 0013.
 */
@Controller
public class LoginContoller {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Map<String, Object> map) {
        return "/index";
    }


    @RequestMapping("/hello")
    public String hello() {
        return "/hello";
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (!userName.equals("") && password != "") {
            //User user =new User(userName,password);
            UserExample example = new UserExample();
            example.or().andNameEqualTo(userName).andPasswordEqualTo(password);
            User user = userService.selectOneByExample(example);
            map.put("result", "1");
            if (null == user) {
                map.put("result", "3");
            } else {
                request.getSession().setAttribute("user", user);
            }

        } else {
            map.put("result", "0");
        }
        return map;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResultEnum logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return ResultEnum.SUCCESS;
    }
}
