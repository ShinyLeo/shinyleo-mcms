package com.shinyleo.mdiy.biz.impl;


import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.mdiy.biz.IModelTemplateBiz;
import com.shinyleo.mdiy.dao.IModelTemplateDao;
import com.shinyleo.mdiy.entity.ModelTemplateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("modelTemplateBiz")
public class ModelTemplateBizImpl extends BaseBizImpl implements IModelTemplateBiz {
	
	/**
	 * 注入模块模版持久化层
	 */
	@Autowired
	private IModelTemplateDao modelTemplateDao;
	
	@Override
	public ModelTemplateEntity getEntity(int appId, int modelId, String key) {
		return modelTemplateDao.getEntity(appId, modelId,key);
	}

	@Override
	public ModelTemplateEntity getEntity(int appId, String key) {
		return modelTemplateDao.getEntityByAppIdAndKey(appId, key);
	}

	/**
	 * 获取模块模版持久化层
	 * @return modelTemplateDao 返回模块模版持久化层
	 */
	@Override
	protected IBaseDao getDao() {
		return modelTemplateDao;
	}

	@Override
	public List queryByAppId(int appId) {
		// TODO Auto-generated method stub
		return modelTemplateDao.queryByAppId(appId);
	}

	
}
