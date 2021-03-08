package com.zhaolw.zoo.boot.controller.form;

import com.zhaolw.zoo.boot.common.form.BasePageForm;

/**
 * Created by Ranj on 2017/7/25 0025.
 */
public class QueryUserForm extends BasePageForm {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
