package com.spring.boot.controller.form;

import com.spring.boot.common.form.BaseForm;

/**
 * Created by Ranj on 2017/7/26 0026.
 */
public class UserForm extends BaseForm {

    private Integer id;

    private String name;

    private String realName;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
