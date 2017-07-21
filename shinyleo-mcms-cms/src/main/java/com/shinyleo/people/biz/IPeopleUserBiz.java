package com.shinyleo.people.biz;

import com.shinyleo.people.entity.PeopleUserEntity;



public interface IPeopleUserBiz extends IPeopleBiz {

	/**
	 * 用户信息实体保存</br>
	 * 只能有子类继承时调用的</br>
	 * @param peopleEntity 用户信息
	 * @return 新增成功后用户ID
	 */
	public int savePeopleUser(PeopleUserEntity peopleEntity);
	
	/**
	 * 更新用户信息,</br>
	 * 只能在有子类时调用</br>
	 * @param peopleEntity 用户信息
	 */
	public void updatePeopleUser(PeopleUserEntity peopleEntity);
	
	/**
	 * 删除用户信息</br>
	 * 只能在有子类时调用</br>
	 * @param peopleId 用户ID
	 */
	public void deletePeopleUser(int peopleId);
	
	/**
	 * 批量删除用户
	 * @param peopleIds 用户id集合
	 */
	public void deletePeopleUsers(int[] peopleIds);
}