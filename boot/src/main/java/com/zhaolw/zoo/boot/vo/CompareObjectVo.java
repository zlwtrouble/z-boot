package com.zhaolw.zoo.boot.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoliwei
 * @version v1.0
 * @date 2019/1/17 13:34
 */
@Data
public class CompareObjectVo {


    /**
     * 旧主表
     **/
    private Object oldObj;

    /**
     * 新主表
     **/
    private Object newObj;

    /**
     * 主表类全路径
     **/
    private String classNameByObject;

    /**
     * 旧明细表
     **/
    private Object oldList;

    /**
     * 新明细表
     **/
    private Object newList;

    /**
     * 明细类全路径
     **/
    private String classNameByList;

    /**
     * productIds
     **/
    private List<Long> productIds;

    /**
     * 不需要传
     **/
    private Map<String, String> productCodeMap;

    /**
     * productId的属性名
     **/
    private String idName;

    /**
     * 明细表放productCode的属性名，不需要传
     **/
    private String codeName;

    /**
     * 1主表和明细表 2主表 3明细表
     **/
    private Integer tableType;

    /**
     * 明细表用：主键名，如果有sku重复这个要传
     **/
    private String primaryKeyName;


    /**
     * 部门id
     **/
    private Long departId;

    /**
     * 部门名称
     **/
    private String departName;

    /**
     * 操作类型
     **/
    private Integer type;

    /**
     * 业务类型
     **/
    private Integer accountType;

    /**
     * 业务主键
     **/
    private Long accountId;

    /**
     * 表名
     **/
    private String tableName;

    /**
     * 操作人名称
     **/
    private String createUserName;

    /**
     * 操作人id
     **/
    private Long createUserId;


}

