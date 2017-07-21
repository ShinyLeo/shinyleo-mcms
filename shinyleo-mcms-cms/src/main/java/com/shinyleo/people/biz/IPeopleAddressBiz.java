
package com.shinyleo.people.biz;


import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.people.constant.e.PeopleAddressEnum;
import com.shinyleo.people.entity.PeopleAddressEntity;

import java.util.List;

/**
 * 
 * 用户收货地址业务层
 * Created by shinyleo on 17/7/20.
 */
public interface IPeopleAddressBiz extends IBaseBiz{
	/**
	 * 通过用户id和站点id查询用户收货地址列表
	 * @param peopleId 用户id
	 * @param appId 站点id
	 * @return 收货地址实体
	 */
	List<PeopleAddressEntity> queryListByAppIdAndPeopleId(int peopleId, int appId);
	
	/**
	 * 根据用户收货地址id和站点id删除收货地址
	 * @param peopleAddressId 用户收货地址id
	 * @param appId 站点id
	 */
	void deleteEntity(int peopleAddressId, int appId);
	
	/**
	 * 根据用户id和地址属性得到地址实体
	 * @param peopleId 用户id
	 * @param addressDefault 地址属性(默认为0，非默认为1)
	 * @return 默认用户收货地址
	 */
	PeopleAddressEntity getDefaultEntity(int peopleId, PeopleAddressEnum addressDefault);
}