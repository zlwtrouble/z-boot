package com.zhaolw.zoo.boot.common.form;

/**
 * Created by Ranj on 2017/7/25 0025.
 */
public class BasePageForm extends BaseForm {

    /**
     * 页码
     */
    private int page = 1;

    /**
     * 页容量
     */
    private int rows = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
