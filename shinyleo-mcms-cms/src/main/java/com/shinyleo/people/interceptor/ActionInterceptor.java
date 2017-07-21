package com.shinyleo.people.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinyleo.base.constant.Const;
import com.shinyleo.basic.interceptor.BaseInterceptor;
import com.shinyleo.people.constant.e.CookieConstEnum;
import com.shinyleo.util.StringUtil;

import net.shinyleo.basic.util.BasicUtil;

/**
 * Created by shinyleo on 17/7/20.
 */
public class ActionInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (this.getSession(request, com.shinyleo.people.constant.e.SessionConstEnum.PEOPLE_SESSION)==null) {
			String loginUrl = this.getCookie(request, CookieConstEnum.PEOPLE_LOGIN_URL);
			if (StringUtil.isBlank(loginUrl)) {
				response.sendRedirect(BasicUtil.getUrl()+"/error/404.do");
				return false;
			} else {
				response.sendRedirect(URLDecoder.decode(loginUrl,Const.UTF8));
				return false;
			}
			
		} else {
			return true;
		}
	}
}