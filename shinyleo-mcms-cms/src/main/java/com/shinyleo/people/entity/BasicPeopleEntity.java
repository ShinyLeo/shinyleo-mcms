

package com.shinyleo.people.entity;

import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.util.*;
import java.util.*;

 /**
 * 通用用户与信息一对多表实体
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：<br/>
 * 历史修订：<br/>
 */
public class BasicPeopleEntity extends BaseEntity {

	private static final long serialVersionUID = 1464746805910L;

	private int bpId; 
	/**
	 * 信息编号
	 */
	private Integer bpBasicId;
	/**
	 * 用户编号
	 */
	private Integer bpPeopleId;
	
	
	/**
	 * 用户信息
	 */
	private PeopleUserEntity peopleUser;
	
	/**
	 * 创建时间
	 */
	private Date bpDatetime;
	
	/**
	 * 设置信息编号
	 */
	public void setBpBasicId(Integer bpBasicId) {
		this.bpBasicId = bpBasicId;
	}

	/**
	 * 获取信息编号
	 */
	public Integer getBpBasicId() {
		return this.bpBasicId;
	}
	
	/**
	 * 设置用户编号
	 */
	public void setBpPeopleId(Integer bpPeopleId) {
		this.bpPeopleId = bpPeopleId;
	}

	/**
	 * 获取用户编号
	 */
	public Integer getBpPeopleId() {
		return this.bpPeopleId;
	}
	
	/**
	 * 设置创建时间
	 */
	public void setBpDatetime(Date bpDatetime) {
		this.bpDatetime = bpDatetime;
	}

	/**
	 * 获取创建时间
	 */
	public Date getBpDatetime() {
		return this.bpDatetime;
	}

	public PeopleUserEntity getPeopleUser() {
		return peopleUser;
	}

	public void setPeopleUser(PeopleUserEntity peopleUser) {
		this.peopleUser = peopleUser;
	}
	
	
	
}