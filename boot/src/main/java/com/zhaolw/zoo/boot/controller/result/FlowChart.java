package com.zhaolw.zoo.boot.controller.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class FlowChart implements Serializable {

    /**
     * 主键id
     **/

    private Long id;

    /**
     * 类型
     **/
    private Integer type;

    /**
     * 流程id（缺货商品主键）
     **/

    private Long processId;

    /**
     * 商品主键
     **/

    private Long productPk;

    /**
     * 商品数量
     **/
    private java.math.BigDecimal number;

    /**
     * 剩余数量
     **/
    private java.math.BigDecimal residueNumber;

    /**
     * 异常数量
     **/
    private java.math.BigDecimal abnormalNumber;

    /**
     * 调拨数量
     **/
    private java.math.BigDecimal allotmentNumber;

    /**
     * 节点状态：1为未完成，2为已完成',
     **/
    private Integer nodeStatus;

    /**
     * sku主键
     **/

    private Long skuPk;

    /**
     * 解决类型：1为调拨，2为采购，3为调拨和采购，4为物权转移，5为取消
     **/
    private Integer dealType;

    /**
     * 供应商主键
     **/

    private Long supplierPk;

    /**
     * 分叉与合并类型
     **/
    private String forkMerge;

    /**
     * 节点单据主键
     **/

    private Long nodeBillPk;

    /**
     * 父节点主键
     **/

    private Long superNodePk;

    /**
     * 关联节点单据主键
     **/

    private Long relationPk;

    /**
     * 操作人
     **/

    private Long operatorUser;

    /**
     * 排序时间
     **/
    private java.util.Date sortTime;

    /**
     * 开始时间
     **/
    private java.util.Date startTime;

    /**
     * 结束时间
     **/
    private java.util.Date endTime;

    /**
     * 采购商品明细主键
     **/

    private Long goodsRelationPk;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 创建人
     **/

    private Long createUser;

    /**
     * 更新时间
     **/
    private java.util.Date updateTime;

    /**
     * 更新人
     **/

    private Long updateUser;

    /**
     * 逻辑删除字段：0为未删除，1为删除
     **/
    private Integer deleted;

    /**
     * 执行描述
     **/
    private String description;


}
