package com.shinyleo.people.action;

import javax.servlet.http.HttpServletRequest;

import com.shinyleo.people.constant.e.SessionConstEnum;
import com.shinyleo.people.entity.PeopleEntity;

/**
 * Created by shinyleo on 17/7/20.
 */
public class BaseAction extends com.shinyleo.mdiy.action.BaseAction {
	/**
	 * 读取国际化资源文件
	 * 
	 * @param key
	 *            ，键值
	 * @return字符串
	 */
	protected String getResString(String key) {
		return super.getResString(key, com.shinyleo.people.constant.Const.RESOURCES);
	}

	/**
	 * 读取国际化资源文件
	 * 
	 * @param key
	 *            键值
	 * @param fullStrs
	 *            需填充的值
	 * @return 字符串
	 */
	protected String getResString(String key, String... fullStrs) {
		return super.getResString(key, com.shinyleo.people.constant.Const.RESOURCES, fullStrs);
	}

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
		this.removeSession(request, SessionConstEnum.PEOPLE_SESSION);
	}
	
	

}