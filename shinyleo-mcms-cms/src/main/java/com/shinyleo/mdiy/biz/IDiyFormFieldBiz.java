package com.shinyleo.mdiy.biz;



import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.mdiy.entity.DiyFormFieldEntity;

import java.util.List;

/**
 * 自定义表单接口
 * Created by shinyleo on 17/7/20.
 */
public interface IDiyFormFieldBiz extends IBaseBiz {
	
	/**
	 * 通过from的id获取实体
	 * @param diyFormId　自定义表单id 
	 * @return　返回实体
	 */
	List<DiyFormFieldEntity> queryByDiyFormId(int diyFormId);
	
	/**
	 * 获取自定义表单字段
	 * @param diyFormFieldFormId　自定义表单id 
	 * @param diyFormFieldFieldName 　自定义表单字段名
	 * @return 返回自定义表单实体
	 */
	DiyFormFieldEntity  getByFieldName(Integer diyFormFieldFormId, String diyFormFieldFieldName);
	
}
