package com.shinyleo.cms.constant.e;

import com.shinyleo.base.constant.e.BaseEnum;

/**
 * Created by shinyleo on 17/7/20.
 */
public enum ColumnTypeEnum implements BaseEnum {
	/**
	 * 列表
	 */
	COLUMN_TYPE_LIST(1),
	/**
	 * 单页
	 */
	COLUMN_TYPE_COVER(2),
	/**
	 * 跳转地址
	 */
	COLUMN_TYPE_URL(3);

	ColumnTypeEnum(Object code) {
		this.code = code;
	}
	
	private Object code;
	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code+"");
	}
	
}