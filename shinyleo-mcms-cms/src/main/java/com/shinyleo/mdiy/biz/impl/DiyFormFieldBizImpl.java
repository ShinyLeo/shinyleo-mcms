package com.shinyleo.mdiy.biz.impl;


import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.mdiy.biz.IDiyFormFieldBiz;
import com.shinyleo.mdiy.dao.IDiyFormFieldDao;
import com.shinyleo.mdiy.entity.DiyFormFieldEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service
public class DiyFormFieldBizImpl extends BaseBizImpl implements IDiyFormFieldBiz {

	/**
	 * 注入自定义表单字段持久化层
	 */
	@Autowired
	private IDiyFormFieldDao diyFormFieldDao;
	
	/**
	 * 获取自定义表单字段持久化层
	 * @return diyFormFieldDao 返回自定义表单字段持久化层
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return diyFormFieldDao;
	}
	@Override
	public List<DiyFormFieldEntity> queryByDiyFormId(int diyFormId) {
		// TODO Auto-generated method stub
		return diyFormFieldDao.queryByDiyFormId(diyFormId);
	}
	@Override
	public DiyFormFieldEntity getByFieldName(Integer diyFormFormId,
			String diyFormFieldFieldName) {
		// TODO Auto-generated method stub
		return diyFormFieldDao.getByFieldName(diyFormFormId, diyFormFieldFieldName);
	}
	

}
