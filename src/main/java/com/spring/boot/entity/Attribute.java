package com.spring.boot.entity;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/7 10:50
 */
public class Attribute {

    private String nature;
    private Long height;

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "nature='" + nature + '\'' +
                ", height=" + height +
                '}';
    }
}
