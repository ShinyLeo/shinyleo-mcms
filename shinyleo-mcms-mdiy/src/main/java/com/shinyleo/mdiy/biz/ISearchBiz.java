package com.shinyleo.mdiy.biz;



import com.shinyleo.basic.biz.IBasicBiz;
import com.shinyleo.mdiy.entity.SearchEntity;
import com.shinyleo.util.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * Created by shinyleo on 17/7/20.
 */
public interface ISearchBiz extends IBasicBiz {
	
	/**
	 * 通过数字区域来查询数据集合
	 * @param cmTableName 表名
	 * @param fieldFieldName 字段名
	 * @param preNum 第一个数
	 * @param nextNum 第二个数
	 * @return 返回数据集合
	 */
	@SuppressWarnings("rawtypes")
	public Map queryMapByNumArea(String cmTableName, String fieldFieldName, int preNum, int nextNum);
	
	/**
	 * 查询列表
	 * @param appId　应用编号
	 * @param page　分页
	 * @return 查询列表
	 */
	public List query(int appId, PageUtil page);
	
	/**
	 * 通过id与应用id获取搜索
	 * @param id　主键
	 * @param appId　应用编号
	 * @return　搜索实体
	 */
	public SearchEntity getByIdAndAppId(int id, int appId);

	/**
	 * 根据appid查询总条数
	 * @param appId 应用id
	 * @return 总数
	 */
	public int queryCount(int appId);
}
