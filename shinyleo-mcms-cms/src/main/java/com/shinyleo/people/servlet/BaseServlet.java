package com.shinyleo.people.servlet;

import javax.servlet.http.HttpServletRequest;

import com.shinyleo.people.constant.e.SessionConstEnum;
import com.shinyleo.people.entity.PeopleEntity;

/**
 会员系统
 * Created by shinyleo on 17/7/20.
 */
public abstract class BaseServlet extends com.shinyleo.basic.servlet.BaseServlet{
 
	/**
	 * 获取用户session.没有返回null
	 */
	protected PeopleEntity getPeopleBySession(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConstEnum.PEOPLE_SESSION);
		if (obj != null) {
			return (PeopleEntity) obj;
		}
		return null;
	}
 
	/**
	 * 设置用户session
	 * 
	 * @param request
	 * @param people
	 *            用户实体
	 */
	protected void setPeopleBySession(HttpServletRequest request, PeopleEntity people) {
		this.setSession(request, SessionConstEnum.PEOPLE_SESSION, people);
	}

	/**
	 * 移除用户session
	 * 
	 * @param request
	 */
	protected void removePeopleBySession(HttpServletRequest request) {
		super.removeSession(request, SessionConstEnum.PEOPLE_SESSION);
	}

}