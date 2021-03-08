package com.zhaolw.zoo.boot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/26 13:37
 **/
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte type;
    private PushMsg msg;
}
