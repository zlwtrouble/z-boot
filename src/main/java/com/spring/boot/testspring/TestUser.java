package com.spring.boot.testspring;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Slf4j
public class TestUser {

    @Resource
    private TestUser2 testUser2;

    public TestUser() {
        log.info("User 构造函数");
    }

    @PostConstruct
    public void haha() {
        log.info("PostConstruct");

    }

    public void init() {
        log.info("init TestUser");

    }


    public void des(String end) {
        log.info("des TestUser");

    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy");

    }


}
