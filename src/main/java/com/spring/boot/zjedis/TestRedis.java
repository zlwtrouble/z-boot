package com.spring.boot.zjedis;

import com.spring.boot.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TestRedis {

    public static void main(String[] args) {
        ZhaoJedisUtil connectRedisUtil = null;
        try {
            connectRedisUtil = ZhaoJedisUtil.getInstance(null, null, "1234");
            connectRedisUtil.set("zhao", DateUtil.formatSimple(new Date()));
            String result = connectRedisUtil.get("zhao");
            log.info("返回结果" + result);

        } catch (Exception e) {
            log.error("访问redis异常", e);
        } finally {
            if (connectRedisUtil != null) {
                connectRedisUtil.close();
            }
        }

    }
}
