package com.shinyleo.cms.parser.impl;



import com.shinyleo.parser.IParser;
import com.shinyleo.util.StringUtil;

/**
 * 文章所在栏目链接(单标签)
 * 文章内容标签
 * {ms:field.typelink/}
 * Created by shinyleo on 17/7/20.
 */
public class ArticleTypeLinkParser extends IParser {
	
	/**
	 * 文章作者标签
	 */
	private final static String ARTICLE_TYPELINK="\\{ms:field.typelink(.*)?/\\}";
	
	private  final static String TYPE = "type";
	
	private final static String TYPE_TOP = "top";
	
	
	/**
	 * 构造标签的属性
	 */
	public ArticleTypeLinkParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(ARTICLE_TYPELINK);
	}
	
	/**
	 *  是否存在type=top的属性
	 * @return　true:存在 false:不存在
	 */
	public boolean  isTop() {
		String temp  = super.getProperty(ARTICLE_TYPELINK).get(TYPE);
		if (StringUtil.isBlank(temp)) {
			return false;
		} else {
			return temp.equalsIgnoreCase(TYPE_TOP) ;
		}
	}

}