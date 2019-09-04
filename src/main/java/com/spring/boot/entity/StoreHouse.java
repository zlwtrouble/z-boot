package com.spring.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xwh
 */
@Data
public class StoreHouse implements Serializable {

	/****/
	private Long id;

	/**仓库编号**/
	private String storeHouseNo;

	/**仓库名称**/
	private String storeHouseName;

	/**仓库类型 中心仓-1 本地仓-2**/
	private Integer storeHouseType;

	/**业务组织**/
	private Long organizeId;

	/**状态 停用-0 启用-1**/
	private Integer status;

	/**仓库地址id**/
	private Long storeHouseAddressId;

	/**经度**/
	private String longitude;

	/**维度**/
	private String dimension;

	/**仓库描述**/
	private String described;

	/**客户id**/
	private Long customerId;

	/**业务组id**/
	private Long businessGroupId;

	/**创建时间**/
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

	/**创建人员 **/
	private Long createUser;

	/**修改时间**/
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	/**修改人员**/
	private Long updateUser;

	/**删除 否-0 是-1**/
	private Integer deleted;

	/**负责人**/
	private Long staffId;

	/**所属中心仓**/
	private Long parentStoreHouseId;

	/**具体地址**/
	private String address;

	/**仓库序号(WMS)**/
	private Long storeId;



}
