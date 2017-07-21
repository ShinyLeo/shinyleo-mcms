
package com.shinyleo.people.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.people.entity.PeopleWebsiteModelEntity;

/**
 * * Created by shinyleo on 17/7/20.
 */
public interface IPeopleWebsiteModelDao extends IBaseDao {

	/**
	 * 根据用户站点模块实体删除据用户站点模块字段
	 * @param peopleWebsiteModel
	 */
	public void deleteEntity(PeopleWebsiteModelEntity peopleWebsiteModel);
	
	/**
	 * 根据用户Id删除用户站点模块中间表数据集合
	 * @param peopleId
	 */
	public void deleteByPeopleId(int peopleId);
	
}