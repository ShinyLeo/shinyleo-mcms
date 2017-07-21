package com.shinyleo.people.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinyleo.basic.biz.ICategoryBiz;
import com.shinyleo.people.biz.IPeopleBiz;
import com.shinyleo.people.biz.IPeopleUserBiz;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.people.biz.IPeopleStudentBiz;
import com.shinyleo.people.dao.IPeopleStudentDao;
import com.shinyleo.basic.entity.CategoryEntity;
import com.shinyleo.people.entity.PeopleStudentEntity;
import com.shinyleo.util.PageUtil;

/**
 * 
 */
@Service("peopleStudentBiz")
public class PeopleStudentBizImpl extends PeopleBizImpl implements IPeopleStudentBiz {
	
	/**
	 * 学生信息持久化层注入
	 */
	@Autowired
	private IPeopleStudentDao peopleStudentDao;
	
	@Autowired
	private IPeopleUserBiz peopleUserBiz;
	
	@Autowired
	private IPeopleBiz peopleBiz;
	
	@Autowired
	private ICategoryBiz categoryBiz;

	@Override
	protected IBaseDao getDao() {
		return peopleStudentDao;
	}
	
	@Override
	public int savePeopleStudent(PeopleStudentEntity peopleStudentEntity) {
		peopleUserBiz.savePeople(peopleStudentEntity);
		return peopleStudentDao.saveEntity(peopleStudentEntity);
	}

	@Override
	public void updatePeopleStudent(PeopleStudentEntity peopleStudentEntity) {
		// TODO Auto-generated method stub
		peopleUserBiz.updateEntity(peopleStudentEntity);
		peopleBiz.updatePeople(peopleStudentEntity);
		this.peopleStudentDao.updateEntity(peopleStudentEntity);
	}

	@Override
	public void deletePeopleStudent(int peopleId) {
		deletePeople(peopleId);
		this.peopleStudentDao.deleteEntity(peopleId);
	}

	@Override
	public PeopleStudentEntity getPeopleStudent(int peopleId) {
		// TODO Auto-generated method stub
		return this.peopleStudentDao.getPeopleStudent(peopleId);
	}

	@Override
	public List<PeopleStudentEntity> queryListPageByAppId(Integer appId,
			PageUtil page) {
		// TODO Auto-generated method stub
		return peopleStudentDao.queryPageListByAppId(appId, page);
	}
	
	
	
	
	@Override
	public Map getStudentInfor(Integer peopleId) {
		
		PeopleStudentEntity peopleStudent = (PeopleStudentEntity) this.peopleStudentDao.getPeopleStudent(peopleId);
		//查询学生的城市信息
		CategoryEntity city = categoryBiz.getCategory(peopleStudent.getPeopleStudentCityID());
		
		
		Map map = new HashMap();
		if(city!=null){
			map.put("city", city.getCategoryTitle());
			CategoryEntity province = (CategoryEntity)categoryBiz.getEntity(city.getCategoryCategoryId());
			if(province!=null){
				map.put("province", province.getCategoryTitle());
			}
		}
		
		map.put("peopleStudent", peopleStudent);
		return map;
	}
	
	
	@Override
	public List<PeopleStudentEntity> queryByMap(Integer appId,PageUtil page ,Map where){
		
		return this.peopleStudentDao.queryByMap(appId, page, where);
	}

	@Override
	public int getCountByMap(Integer appId, Map where) {
		// TODO Auto-generated method stub
		return this.peopleStudentDao.getCountByMap(appId, where);
	}
	
	
}