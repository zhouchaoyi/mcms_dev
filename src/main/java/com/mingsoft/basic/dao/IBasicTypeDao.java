package com.mingsoft.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.BasicTypeEntity;

/**
 * ms-basic
 * 
 * @author 小小
 * @version 版本号：<br/>
 *          创建日期：2016年4月23日<br/>
 *          历史修订：<br/>
 */
public interface IBasicTypeDao extends IBaseDao {

	/**
	 * 删除基础信息对应属性
	 * 
	 * @param basicId
	 *            基础信息编号
	 * @param basicTypeId
	 *            基础信息类型编号
	 */
	void delete(@Param("basicId") int basicId, @Param("basicTypeId") int basicTypeId);

	/**
	 * 删除基础信息关联属性
	 * 
	 * @param basicId
	 *            基础信息编号
	 */
	void deleteByBasicId(@Param("basicId") int basicId);

	/**
	 * 获取关联属性
	 * @param basicId 信息编号
	 * @param basicTypeId 信息属性
	 * @return
	 */
	BasicTypeEntity getByBasicIdAndBasicTypeId(@Param("basicId") int basicId,
											   @Param("basicTypeId") int basicTypeId);

	/**
	 * 查询基础信息关联属性
	 * @param basicId 信息编号
	 * @return 
	 */
	List<BasicTypeEntity> queryByBasicId(@Param("basicId") int basicId);
	
	/**
	 * 查询基础信息关联属性
	 * 
	 * @param basicTypeTitle
	 *            属性名称
	 * @return
	 */
	List<BasicTypeEntity> queryByBasicTypeTitle(@Param("basicId") String[] basicTypeTitles);
}
