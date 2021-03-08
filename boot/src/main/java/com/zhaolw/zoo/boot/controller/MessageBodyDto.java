package com.zhaolw.zoo.boot.controller;

import lombok.Data;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/4/9 10:04
 **/
@Data
public class MessageBodyDto {

    private String sign;
    private String mchId;
    private String phone;
    private String node;
    private String content;
}
