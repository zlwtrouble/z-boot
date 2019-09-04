package com.spring.boot.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/25 18:26
 **/
public class TestNewRequest {


    public static void main(String[] args) {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }
}
