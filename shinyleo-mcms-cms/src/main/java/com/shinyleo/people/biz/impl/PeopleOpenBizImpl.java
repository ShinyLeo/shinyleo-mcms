
package com.shinyleo.people.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.people.biz.IPeopleOpenBiz;
import com.shinyleo.people.dao.IPeopleDao;
import com.shinyleo.people.dao.IPeopleOpenDao;
import com.shinyleo.people.dao.IPeopleUserDao;
import com.shinyleo.people.entity.PeopleOpenEntity;

/**
 * 开发平台用户
 */
@Service("peopleOpenBizImpl")
public class PeopleOpenBizImpl extends PeopleUserBizImpl implements IPeopleOpenBiz {

	@Autowired
	private IPeopleOpenDao peopleOpenDao;
	
	/**
	 * 用户信息持久化层注入
	 */
	@Autowired
	private IPeopleUserDao peopleUserDao; 
	
	
	@Autowired
	private IPeopleDao peopleDao; 

	@Override
	protected IBaseDao getDao() {
		return peopleOpenDao;
	}

	@Override
	public void savePeopleOpen(PeopleOpenEntity peopleOpen) {
		// TODO Auto-generated method stub
		peopleDao.saveEntity(peopleOpen);
		peopleUserDao.saveEntity(peopleOpen);
		peopleOpenDao.saveEntity(peopleOpen);
	}

	@Override
	public PeopleOpenEntity getByOpenId(String openId) {
		// TODO Auto-generated method stub
		return  peopleOpenDao.getByOpenId(openId);
	}

}