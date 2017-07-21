package com.shinyleo.people.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.people.biz.IPeopleUserBiz;
import com.shinyleo.people.dao.IPeopleUserDao;
import com.shinyleo.people.entity.PeopleUserEntity;

/**
 * 
 */
@Service("peopleUserBiz")
public class PeopleUserBizImpl extends PeopleBizImpl implements IPeopleUserBiz {

	/**
	 * 用户信息持久化层注入
	 */
	@Autowired
	private IPeopleUserDao peopleUserDao;

	@Override
	protected IBaseDao getDao() {
		return peopleUserDao;
	}

	/**
	 * 用户信息实体保存</br>
	 * 只能在有子类继承时调用的</br>
	 */
	public int savePeopleUser(PeopleUserEntity peopleEntity){
		savePeople(peopleEntity);
		return peopleUserDao.saveEntity(peopleEntity);
	}	
	
	/**
	 * 更新用户信息</br>
	 * 只能在有子类时调用</br>
	 * @param peopleEntity 用户信息
	 */
	public void updatePeopleUser(PeopleUserEntity peopleEntity){
		updatePeople(peopleEntity);
		this.peopleUserDao.updateEntity(peopleEntity);
	}

	/**
	 * 删除用户信息</br>
	 * 只能在有子类时调用</br>
	 * @param peopleId 用户ID
	 */
	public void deletePeopleUser(int peopleId){
		deletePeople(peopleId);
		this.peopleUserDao.deleteEntity(peopleId);
	}	
	
	@Override
	public void deletePeopleUsers(int[] peopleIds) {
		if(peopleIds==null){
			return;
		}
		this.deletePeople(peopleIds);
		this.peopleUserDao.deletePeopleUsers(peopleIds);
	}

	
}