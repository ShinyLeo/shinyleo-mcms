package com.shinyleo.mdiy.entity;

import com.shinyleo.base.entity.BaseEntity;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public class ContentModelEntity extends BaseEntity {
 
	/**
	 * 自增长ID
	 */
	private int cmId;
	
	/**
	 * 表名提示文字
	 */
	private String cmTipsName;
	
	/**
	 * 表单名称
	 */
	private String cmTableName;
	
	/**
	 * 表单所属的管理员id
	 */
	private int cmManagerId;
	
	/**
	 * 自定义模型模块编号
	 */
	private int cmModelId;
	

	/**
	 * 获取cmId
	 * @return cmId
	 */
	public int getCmId() {
		return cmId;
	}

	/**
	 * 设置cmId
	 * @param cmId
	 */
	public void setCmId(int cmId) {
		this.cmId = cmId;
	}

	/**
	 * 获取cmTipsName
	 * @return cmTipsName
	 */
	public String getCmTipsName() {
		return cmTipsName;
	}

	/**
	 * 设置cmTipsName
	 * @param cmTipsName
	 */
	public void setCmTipsName(String cmTipsName) {
		this.cmTipsName = cmTipsName;
	}

	/**
	 * 获取cmTableName
	 * @return cmTableName
	 */
	public String getCmTableName() {
		return cmTableName;
	}

	/**
	 * 设置cmTableName
	 * @param cmTableName
	 */
	public void setCmTableName(String cmTableName) {
		this.cmTableName = cmTableName;
	}
	
	/**
	 * 获取该表单所属的管理员id
	 * @return
	 */
	public int getCmManagerId() {
		return cmManagerId;
	}
	
	/**
	 * 设置该表单所属的管理员id
	 * @param cmManagerId
	 */
	public void setCmManagerId(int cmManagerId) {
		this.cmManagerId = cmManagerId;
	}

	public int getCmModelId() {
		return cmModelId;
	}

	public void setCmModelId(int cmModelId) {
		this.cmModelId = cmModelId;
	}
	
}
