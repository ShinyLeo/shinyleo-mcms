package com.shinyleo.people.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.people.entity.PeopleWebsiteModelEntity;


public interface IPeopleWebsiteModelBiz extends IBaseBiz {

	/**
	 * 根据用户站点模块实体删除据用户站点模块字段
	 * @param peopleWebsiteModel
	 */
	public void deleteEntity(PeopleWebsiteModelEntity peopleWebsiteModel);
	
}