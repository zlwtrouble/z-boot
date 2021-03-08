package com.zhaolw.zoo.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 盘点任务
 *
 * @author zhaoliwe
 */
@Data
public class InventoryTask implements Serializable {

    /**
     * 主键
     **/
    private Long id;

    /**
     * 仓库id
     **/
    private Long warehouseId;

    /**
     * 运行次数
     **/
    private Integer runCount;

    /**
     * 状态0. 取消，1.初始化 2.进行中.3.完成
     **/
    private Integer status;


    /**
     * 取整类型 1向下取整 2向上取整
     **/
    private Integer IntegerType;

    /**
     * 包含库存是否为零 0否 ，1是
     **/
    private Integer hasZero;

    /**
     * 货架数
     **/
    private Integer shelfCount;

    /**
     * 天数
     **/
    private Integer dayCount;

    /**
     * 操作时间
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date operationTime;

    /**
     * 操作人id
     **/
    private Long operationUserId;

    /**
     * 详情
     **/
    private String description;

    /**
     * 任务状态
     **/
    private String taskStatus;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 创建人员
     **/
    private Long createUser;

    /**
     * 修改时间
     **/
    private java.util.Date updateTime;

    /**
     * 修改人员
     **/
    private Long updateUser;

    /**
     * 删除 否-0 是-1
     **/
    private Integer deleted;


}
