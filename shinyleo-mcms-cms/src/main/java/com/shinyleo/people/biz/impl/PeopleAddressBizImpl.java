package com.shinyleo.people.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.people.biz.IPeopleAddressBiz;
import com.shinyleo.people.constant.e.PeopleAddressEnum;
import com.shinyleo.people.dao.IPeopleAddressDao;
import com.shinyleo.people.entity.PeopleAddressEntity;
/**
 * 
 * 用户收货地址业务处理层
 * @author yangxy
 * @version 
 * 版本号：【100-000-000】
 * 创建日期：2015年8月23日 
 * 历史修订：
 */
@Service("peopleAddressBizImpl")
public class PeopleAddressBizImpl extends BaseBizImpl implements IPeopleAddressBiz{
	
	/**
	 * 注入用户收货地址持久层
	 */
	@Autowired
	private IPeopleAddressDao peopleAddressDao;
	
	/**
	 * 获取peopleAddressDao
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return peopleAddressDao;
	}

	@Override
	public List<PeopleAddressEntity> queryListByAppIdAndPeopleId(int peopleId, int appId) {
		// TODO Auto-generated method stub
		return peopleAddressDao.queryListByAppIdAndPeopleId(peopleId, appId);
	}

	@Override
	public void deleteEntity(int peopleAddressId, int appId) {
		// TODO Auto-generated method stub
		peopleAddressDao.deleteEntity(peopleAddressId, appId);
	}

	@Override
	public PeopleAddressEntity getDefaultEntity(int peopleId, PeopleAddressEnum addressDefault) {
		// TODO Auto-generated method stub
		return peopleAddressDao.getDefaultEntity(peopleId, addressDefault.toInt());
	}
}