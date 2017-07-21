package com.shinyleo.people.dao;


import org.apache.ibatis.annotations.Param;

import com.shinyleo.base.dao.IBaseDao;

/**
 *
 * * Created by shinyleo on 17/7/20.
 */
public interface IPeopleUserDao extends IBaseDao {
	/**
	 * 根据用户id集合批量删除用户
	 * @param peopleIds 用户id集合
	 */
	public void deletePeopleUsers(@Param("peopleIds") int[] peopleIds);
}