package com.shinyleo.mdiy.biz.impl;


import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.impl.BasicBizImpl;
import com.shinyleo.mdiy.biz.ISearchBiz;
import com.shinyleo.mdiy.dao.ISearchDao;
import com.shinyleo.mdiy.entity.SearchEntity;
import com.shinyleo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("searchBiz")
public class SearchBizImpl extends BasicBizImpl implements ISearchBiz {

	/**
	 * 搜索持久化层
	 */
	@Autowired
	private ISearchDao searchDao;
	
	/**
	 * 获取searchDao
	 */
	@Override
	protected IBaseDao getDao() {
		return searchDao;
	}
	
	/**
	 * 通过数字区域来查询数据集合
	 * @param cmTableName 表名
	 * @param fieldFieldName 字段名
	 * @param preNum 第一个数
	 * @param nextNum 第二个数
	 * @return 返回数据集合
	 */
	@SuppressWarnings("rawtypes")
	public Map queryMapByNumArea(String cmTableName,String fieldFieldName,int preNum,int nextNum){
		return searchDao.queryMapByNumArea(cmTableName, fieldFieldName, preNum, nextNum);
	}
	
	/**
	 * 查询列表
	 * @param appId　应用编号
	 * @param page　分页
	 */
	@Override
	public List query( int appId,PageUtil page) {
		return searchDao.query(appId,page.getPageNo()*page.getPageSize(),page.getPageSize());
	}
	
	
	/**
	 * 
	 */
	@Override
	public SearchEntity getByIdAndAppId(int id, int appId) {
		return searchDao.getByIdAndAppId(id, appId);
	}

	@Override
	public int queryCount(int appId) {
		return searchDao.queryCount(appId);
	}
}
