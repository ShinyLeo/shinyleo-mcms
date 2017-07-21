
package com.shinyleo.people.aop;

import javax.servlet.http.HttpServletRequest;

import com.shinyleo.base.entity.SessionEntity;
import com.shinyleo.people.constant.e.SessionConstEnum;

import net.shinyleo.basic.util.BasicUtil;

/**
 * bbs切面基础方法
 * Created by shinyleo on 17/7/20.
 */
public abstract class BaseAop extends com.shinyleo.basic.aop.BaseAop {


	/**
	 * 读取用户sessoin
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回获取到的用户session,获取不到返回nul
	 */
	@Deprecated
	protected SessionEntity getPeopleBySession(HttpServletRequest request) {
		// 传入用户请求，读取用户的session || super,调用父类的protected属性的getSession方法
		Object obj = this.getSession(request, SessionConstEnum.PEOPLE_SESSION);
		if (obj != null) {
			// 返回用户的所有信息
			return (SessionEntity) obj;
		}
		return null;
	}
	
	/**
	 * 读取用户sessoin
	 * 
	 *            HttpServletRequest对象
	 * @return 返回获取到的用户session,获取不到返回nul
	 */
	protected SessionEntity getPeopleBySession() {
		// 传入用户请求，读取用户的session || super,调用父类的protected属性的getSession方法
		Object obj = BasicUtil.getSession(SessionConstEnum.PEOPLE_SESSION);
		if (obj != null) {
			// 返回用户的所有信息
			return (SessionEntity) obj;
		}
		return null;
	}

}