package com.shinyleo.mdiy.action.web;

import com.shinyleo.basic.parser.BaseParser;
import com.shinyleo.mdiy.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("webDiyPath")
@RequestMapping("/")
public class DiyPageAction extends BaseAction {

	@Autowired
	private BaseParser parser;

	/**
	 * 自定义页码
	 * 
	 */
	@RequestMapping(value = "/{diy}")
	public void diy(@PathVariable(value = "diy") String diy, HttpServletRequest req, HttpServletResponse resp) {
		String content = this.generaterPage(diy, parser, req);
		this.outString(resp, content);
	}
}
