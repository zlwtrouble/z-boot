package com.spring.boot.testspring;

import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

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

    @Bean(initMethod = "init", destroyMethod = "des")
    public TestUser testUser() {
        return new TestUser();
    }
}
