package com.shinyleo.mdiy.entity;


import com.shinyleo.base.entity.BaseEntity;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public class ModelTemplateEntity extends BaseEntity {

    /**
     * 自动编号
     */
    private int modelTemplateId;

	/**
     * 应用编号
     */
    private int modelTemplateAppId;

    /**
     * 模块的编号
     */
    private int modelTemplateModelId;

    /**
     * 对应路径
     */
    private String modelTemplatePath;
    
    /**
     * 标题
     */
    private String modelTemplateTitle;
    
    
    public String getModelTemplateTitle() {
		return modelTemplateTitle;
	}

	public void setModelTemplateTitle(String modelTemplateTitle) {
		this.modelTemplateTitle = modelTemplateTitle;
	}

	/**
     * 路径值
     */
    private String modelTemplateKey;
    

	public String getModelTemplateKey() {
		return modelTemplateKey;
	}

	public void setModelTemplateKey(String modelTemplateKey) {
		this.modelTemplateKey = modelTemplateKey;
	}

	public int getModelTemplateAppId() {
		return modelTemplateAppId;
	}

	public void setModelTemplateAppId(int modelTemplateAppId) {
		this.modelTemplateAppId = modelTemplateAppId;
	}

	public int getModelTemplateModelId() {
		return modelTemplateModelId;
	}

	public void setModelTemplateModelId(int modelTemplateModelId) {
		this.modelTemplateModelId = modelTemplateModelId;
	}

	public String getModelTemplatePath() {
		return modelTemplatePath;
	}

	public void setModelTemplatePath(String modelTemplatePath) {
		this.modelTemplatePath = modelTemplatePath;
	}
	
    public int getModelTemplateId() {
		return modelTemplateId;
	}

	public void setModelTemplateId(int modelTemplateId) {
		this.modelTemplateId = modelTemplateId;
	}

}
