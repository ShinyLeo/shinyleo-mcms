package com.shinyleo.people.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.util.*;
import java.util.*;
import com.shinyleo.people.entity.BasicPeopleEntity;
import com.shinyleo.people.biz.IBasicPeopleBiz;
import com.shinyleo.people.dao.IBasicPeopleDao;

/**
 * 通用用户与信息一对多表管理持久化层
 */
 @Service("basicPeopleBizImpl")
public class BasicPeopleBizImpl extends BaseBizImpl implements IBasicPeopleBiz {

	
	@Autowired
	private IBasicPeopleDao basicPeopleDao;
	
	
		@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return basicPeopleDao;
	} 
	
	@Override
	public List query(BasicPeopleEntity basicPeople) {
		return basicPeopleDao.query(basicPeople);
	}

	@Override
	public void saveBasicPeople(BasicPeopleEntity basicPeople) {
		// TODO Auto-generated method stub
		basicPeople.setBpDatetime(new Date());
		basicPeopleDao.saveEntity(basicPeople);
		saveEntity(basicPeople);
	}

	@Override
	public void deleteBasicPeople(BasicPeopleEntity basicPeople) {
		// TODO Auto-generated method stub
		basicPeopleDao.deleteByEntity(basicPeople);
		deleteEntity(basicPeople);
		
	}	
}