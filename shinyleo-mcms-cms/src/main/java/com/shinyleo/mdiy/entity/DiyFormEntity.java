package com.shinyleo.mdiy.entity;


import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.util.AESUtil;
import com.shinyleo.util.StringUtil;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public class DiyFormEntity extends BaseEntity {
	/**
	 * 自增长ID
	 */
	private int diyFormId;
	
	/**
	 * 表名提示文字
	 */
	private String diyFormTipsName;
	
	/**
	 * 表单名称
	 */
	private String diyFormTableName;
	
	/**
	 * 表单所属的管理员id
	 */
	private int diyFormManagerId;
	
	/**
	 * 表单所属的管理员id
	 */
	private int diyFormAppId;
	
	/**
	 * 表单的访问地址
	 */
	private String diyFormUrl;

	public int getDiyFormId() {
		return diyFormId;
	}

	public void setDiyFormId(int diyFormId) {
		this.diyFormId = diyFormId;
	}

	public String getDiyFormTipsName() {
		return diyFormTipsName;
	}

	public void setDiyFormTipsName(String diyFormTipsName) {
		this.diyFormTipsName = diyFormTipsName;
	}

	public String getDiyFormTableName() {
		return diyFormTableName;
	}

	public void setDiyFormTableName(String diyFormTableName) {
		this.diyFormTableName = diyFormTableName;
	}

	public int getDiyFormManagerId() {
		return diyFormManagerId;
	}

	public void setDiyFormManagerId(int diyFormManagerId) {
		this.diyFormManagerId = diyFormManagerId;
	}

	public int getDiyFormAppId() {
		return diyFormAppId;
	}

	public void setDiyFormAppId(int diyFormAppId) {
		this.diyFormAppId = diyFormAppId;
		
	}

	public String getDiyFormUrl() {
		diyFormUrl= AESUtil.encrypt(this.diyFormId+"", StringUtil.Md5(this.diyFormAppId+"").substring(16));
		return diyFormUrl;
	}

	public void setDiyFormUrl(String diyFormUrl) {
		this.diyFormUrl = diyFormUrl;
	}
	
	
}
