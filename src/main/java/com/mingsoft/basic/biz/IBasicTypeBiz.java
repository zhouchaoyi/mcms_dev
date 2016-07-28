package com.mingsoft.basic.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.entity.BasicTypeEntity;

/**
 * ms-basic
 * 
 * @author 小小
 * @version 版本号：<br/>
 *          创建日期：2016年4月23日<br/>
 *          历史修订：<br/>
 */
public interface IBasicTypeBiz extends IBaseBiz {

	/**
	 * 删除基础信息对应属性
	 * 
	 * @param basicId
	 *            基础信息编号
	 * @param basicTypeId
	 *            基础信息类型编号
	 */
	void delete(int basicId, int basicTypeId);

	/**
	 * 删除基础信息关联属性
	 * 
	 * @param basicId
	 *            基础信息编号
	 */
	void deleteByBasicId(int basicId);

	/**
	 * 获取关联属性
	 * 
	 * @param basicId
	 *            信息编号
	 * @param basicTypeId
	 *            信息属性
	 * @return
	 */
	BasicTypeEntity getByBasicIdAndBasicTypeId(int basicId, int basicTypeId);

	/**
	 * 查询基础信息关联属性
	 * 
	 * @param basicId
	 *            信息编号
	 * @return
	 */
	List<BasicTypeEntity> queryByBasicId(int basicId);
	
	
}
