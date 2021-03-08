
package com.zhaolw.zoo.boot.entity;

import lombok.Data;

/**
 * @author weitao
 * @version $$Id: CompareValue.java, v 0.1 2019/1/16 16:10 weitao Exp $$
 */
@Data
public class CompareValue {

    /**
     * 属性
     */
    private String field;

    /**
     * 原值
     */
    private String oldVal;

    /**
     * 现值
     */
    private String newVal;
}
