package com.mingsoft.basic.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IBasicTypeBiz;
import com.mingsoft.basic.dao.IBasicTypeDao;
import com.mingsoft.basic.entity.BasicTypeEntity;

/**
 * ms-basic
 * 
 * @author 小小
 * @version 版本号：<br/>
 *          创建日期：2016年4月23日<br/>
 *          历史修订：<br/>
 */
@Service("basicTypeBiz")
public class BasicTypeBizImpl extends BaseBizImpl implements IBasicTypeBiz {

	@Autowired
	private IBasicTypeDao basicTypeDao;

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return basicTypeDao;
	}

	@Override
	public void delete(int basicId, int basicTypeId) {
		// TODO Auto-generated method stub
		basicTypeDao.delete(basicId, basicTypeId);
	}

	@Override
	public void deleteByBasicId(int basicId) {
		// TODO Auto-generated method stub
		basicTypeDao.deleteByBasicId(basicId);
	}

	@Override
	public BasicTypeEntity getByBasicIdAndBasicTypeId(int basicId, int basicTypeId) {
		// TODO Auto-generated method stub
		return basicTypeDao.getByBasicIdAndBasicTypeId(basicId, basicTypeId);
	}

	@Override
	public List<BasicTypeEntity> queryByBasicId(int basicId) {
		// TODO Auto-generated method stub
		return basicTypeDao.queryByBasicId(basicId);
	}

}
