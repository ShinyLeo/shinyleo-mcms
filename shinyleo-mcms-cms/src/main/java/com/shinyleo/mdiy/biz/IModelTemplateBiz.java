package com.shinyleo.mdiy.biz;


import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.mdiy.entity.ModelTemplateEntity;

import java.util.List;

/**
 * 模块模版业务接口
 * Created by shinyleo on 17/7/20.
 */
public interface IModelTemplateBiz extends IBaseBiz {

	/**
	 * 获取模版路径
	 * @param appId 应用编号
	 * @param modelId　模块编号
	 * @param key　模块关键字
	 * @return　返回模块模版
	 */
	ModelTemplateEntity getEntity(int appId, int modelId, String key);
	
	/**
	 * 获取模版路径
	 * @param appId 应用编号
	 * @param key　模块关键字
	 * @return　返回模块模版
	 */
	ModelTemplateEntity getEntity(int appId, String key);
	
	
	/**
	 * 查询当前应用下面的所有自定义页面
	 * @param appId　应用编号
	 * @return　返回记录集合
	 */
	List queryByAppId(int appId);
}
