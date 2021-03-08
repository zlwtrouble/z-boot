package com.zhaolw.zoo.boot.testspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class TestConfig {

    @Resource
    private ApplicationContext applicationContext;


    @PostConstruct
    public void init() {
        applicationContext.publishEvent(new ApplicationEvent("手动发版时间") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });


    }

}
