
package com.shinyleo.cms.parser.impl;

import com.shinyleo.parser.IParser;

/**
 * 内容发布作者(单标签)
 * Created by shinyleo on 17/7/20.
 */
public class ArticleAuthorParser extends IParser {
	
	/**
	 * 文章作者标签
	 */
	private final static String ARTICLE_AUTHOR_FIELD="\\{ms:field.author/\\}";
	
	/**
	 * 构造标签的属性
	 */
	public ArticleAuthorParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(ARTICLE_AUTHOR_FIELD);
	}

}