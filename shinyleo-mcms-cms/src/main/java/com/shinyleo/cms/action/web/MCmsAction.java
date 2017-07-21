
package com.shinyleo.cms.action.web;


import com.shinyleo.cms.parser.CmsParser;
import com.shinyleo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by shinyleo on 17/7/20.
 * <p>
 * Comments:该类作废，自定义页面请调用对应模块下面的DynamicPageAction
 * </p>
 * <p>
 * Modification history:动态生成页面，需要后台配置自定义页数据
 * </p>
 */
@Controller("dynamicPageAction")
@RequestMapping("/mcms")
public class MCmsAction extends com.shinyleo.mdiy.action.BaseAction{
	
	
	/**
	 * 替换时间的字符
	 */
	private String date = "{date/}";
	/**
	 * 替换主机地址
	 */
	private String host = "{host/}"; 
	/**
	 *文章解析器
	 */
	@Autowired
	private CmsParser cmsParser;
	
	
	//该类作废，自定义页面路径请填写对应模块下面的DynamicPageAction的路径
	//如商城就为:/mall/{key}.do
	/**
	 * 前段会员中心所有页面都可以使用该方法 请求地址例如：　／{diy}.do,例如登陆界面，与注册界面都可以使用
	 * 
	 */
	@RequestMapping("/{diy}.do")
	@ExceptionHandler(NullPointerException.class)
	public void diy(@PathVariable(value = "diy") String diy, HttpServletRequest req, HttpServletResponse resp) {
		String content = this.generaterPage("mcms/" + diy,cmsParser,req);
		if (StringUtil.isBlank(content)) {
			this.outString(resp, this.getResString("err.not.exist","mcms/"+diy));
			return;
		}
		//增加时间
		content =  content.replace(date, StringUtil.getDateSimpleStr());
		content =  content.replace(host, this.getApp(req).getAppHostUrl());
		this.outString(resp, content);
	}
	
}