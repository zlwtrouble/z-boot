package com.zhaolw.zoo.boot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/26 13:33
 **/
@Data
public class PushMsg implements Serializable {
    private static final long serialVersionUID = 2L;
    public String author_name;
    public String date;
    //图片链接
    public String thumbnail_pic_s;
    public String title;
    //详情链接
    public String url;

}
