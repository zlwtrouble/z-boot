package com.spring.boot.controller.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {

	/**主键id**/

	private Long id;


	/**异常数量**/
	private java.math.BigDecimal receiptGoodsAmount;


	private Long createUser;

	/**更新时间**/
	private java.util.Date updateTime;

	/**更新人**/

	private Long updateUser;

	/**逻辑删除字段：0为未删除，1为删除**/
	private Integer deleted;



}
