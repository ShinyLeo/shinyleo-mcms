

package com.shinyleo.cms.parser.impl;

import com.shinyleo.parser.IParser;

/**
 * * Created by shinyleo on 17/7/20.
 */
public class ArticleLitpicParser extends IParser {
	/**
	 * 文章缩略标签
	 */
	private final static String ARTICLE_LITPIC_FIELD="\\{ms:field.litpic/\\}";
	
	/**
	 * 构造标签的属性
	 */
	public ArticleLitpicParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(ARTICLE_LITPIC_FIELD);
	}
}