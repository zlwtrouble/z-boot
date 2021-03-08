package com.zhaolw.zoo.boot.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoliwe
 */
@Data
public class AgentInfoDetail implements Serializable {

    /**
     * 主键
     **/

    private Long id;


    /**
     * 类型
     **/
    private Integer type;


    /**
     * /**
     * 关联单据的时间
     **/
    private java.util.Date referTime;


}
