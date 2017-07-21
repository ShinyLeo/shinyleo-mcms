
package com.shinyleo.people.constant;

import com.shinyleo.base.constant.e.BaseEnum;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public enum ModelCode implements BaseEnum{

	/**
	 * 用户信息模块
	 */
	PEOPLE("07000000"),
	
	/**
	 * 用户注册
	 */
	PEOPLE_REGISTER("07010100"),
	
	/**
	 * 用户登录
	 */
	PEOPLE_LOGIN("07010200"),
	
	/**
	 * 普通用户管理
	 */
	PEOPLE_USER("07020100");
	

	
	/**
	 * 设置modelCode的常量
	 * @param code 常量
	 */
	ModelCode(String code) {
		this.code = code;
	}

	private String code;

	/**
	 * 返回该modelCode常量的字符串表示
	 * @return 字符串
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code;
	}

	/**
	 * 返回该modelCode常量的整型表示
	 * @return 整型
	 */
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.parseInt(code);
	}
}