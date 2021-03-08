package com.zhaolw.zoo.boot.testspring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Slf4j
@Component
public class TestUser2 {

    @Resource
    private TestUser testUser;

    public TestUser2() {
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
