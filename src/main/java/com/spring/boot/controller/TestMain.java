package com.spring.boot.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/10 16:08
 **/
@Slf4j
public class TestMain {
     public static void main(String[] args) {

         log.info("输出args:"+ JSONObject.toJSONString(args));
      }
}
