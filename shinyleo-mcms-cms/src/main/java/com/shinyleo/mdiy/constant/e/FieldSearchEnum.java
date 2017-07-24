package com.shinyleo.mdiy.constant.e;


import com.shinyleo.base.constant.e.BaseEnum;

/**
 * Created by shinyleo on 17/7/20.
 */
public enum FieldSearchEnum  implements BaseEnum {
		/**
		 * 不支持搜索
		 */
		NOT("0"),
		/**
		 * 支持搜索
		 */
		IS("1");

	FieldSearchEnum(Object code) {
		this.code = code;
	}
	
	private Object code;
	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code+"");
	}

}
