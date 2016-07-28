package com.mingsoft.basic.entity;

import com.mingsoft.base.entity.BaseEntity;

/**
 * ms-basic
 * @author 小小
 * @version 
 * 版本号：<br/>
 * 创建日期：2016年4月23日<br/>
 * 历史修订：<br/>
 */
public class BasicTypeEntity  extends BaseEntity{

	/**
	 * 关联basicId
	 */
	private int basicTypeBasicId;
	
	/**
	 * 基础信息属性
	 */
	private int basicTypeType;
	
	
	public BasicTypeEntity(int basicId,int type) {
		this.basicTypeBasicId = basicId;
		this.basicTypeType = type;
	}


	public int getBasicTypeBasicId() {
		return basicTypeBasicId;
	}


	public void setBasicTypeBasicId(int basicTypeBasicId) {
		this.basicTypeBasicId = basicTypeBasicId;
	}


	public int getBasicTypeType() {
		return basicTypeType;
	}


	public void setBasicTypeType(int basicTypeType) {
		this.basicTypeType = basicTypeType;
	}

	
}
