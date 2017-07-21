
package com.shinyleo.people.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.people.biz.IPeopleWebsiteModelBiz;
import com.shinyleo.people.dao.IPeopleWebsiteModelDao;
import com.shinyleo.people.entity.PeopleWebsiteModelEntity;

@Service("peopleWebsiteModelBiz")
public class PeopleWebsiteModelBizImpl extends BaseBizImpl implements IPeopleWebsiteModelBiz {

	/**
	 * 用户站点模块持久化层注入
	 */
	@Autowired
	private IPeopleWebsiteModelDao peopleWebsiteModelDao;
	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return peopleWebsiteModelDao;
	}

	/**
	 * 根据用户站点模块实体删除据用户站点模块字段
	 * @param peopleWebsiteModel
	 */
	public void deleteEntity(PeopleWebsiteModelEntity peopleWebsiteModel ){
		peopleWebsiteModelDao.deleteEntity(peopleWebsiteModel);
	}
	
}