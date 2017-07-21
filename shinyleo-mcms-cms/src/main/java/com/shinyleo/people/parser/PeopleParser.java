package com.shinyleo.people.parser;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shinyleo.basic.parser.IGeneralParser;

/**
 * Created by shinyleo on 17/7/20.
 */
@Component
@Scope("prototype")
public class PeopleParser extends IGeneralParser {

	@Override
	public String parse(String html,Object... obj) {
		super.htmlContent = html;
		init(obj);
		htmlContent = parseGeneral();
		return htmlContent;
	}


}