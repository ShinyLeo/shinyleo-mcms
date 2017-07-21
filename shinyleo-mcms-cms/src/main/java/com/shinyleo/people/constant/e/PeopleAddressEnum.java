package com.shinyleo.people.constant.e;

import com.shinyleo.base.constant.e.BaseEnum;

/** 
 * 用户收货地址状态枚举类
 * Created by shinyleo on 17/7/20.
 */
public enum PeopleAddressEnum implements BaseEnum{
	/**
	 * 默认收货地址
	 */
	ADDRESS_DEFAULT(0),
	
	/**
	 * 非默认收货地址
	 */
	ADDRESS_NOT_DEFAULT(1);
	
	PeopleAddressEnum(Object code) {
		this.code = code;
	}  

	private Object code;

	@Override
	public String toString() {
		return code.toString();
	}

	public int toInt() {
		return Integer.parseInt(code.toString());
	}
}