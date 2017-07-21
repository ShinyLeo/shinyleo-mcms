package com.shinyleo.mdiy.biz;



import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.mdiy.entity.ContentModelEntity;
import com.shinyleo.util.PageUtil;

import java.util.List;


 /**
 * Created by shinyleo on 17/7/20.
 */
public interface IContentModelBiz extends IBaseBiz {
	
	/**
	 * 根据内容模型的表名查找实体
	 * @param cmTableName 表名
	 * @return 内容模型实体
	 */
	public ContentModelEntity getContentModelByTableName(String cmTableName);
	
	/**
	 * 根据管理员Id查找内容模型的总数
	 * @param cmManagerId 管理员id
	 * @return 记录总数
	 */
	public int getContentModelByManagerId(int cmManagerId);
	
	/**
	 * 分页查询指定管理员的表单内容模型
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @param cmManagerId 管理员id
	 * @return 表单内容模型
	 */
	List<BaseEntity> queryPageByManagerId(PageUtil page, String orderBy, boolean order, int cmManagerId);
	
	/**
	 * 根据管理员id查找内容模型实体
	 * @param cmManagerId
	 * @return 表单内容模型
	 */
	public List<BaseEntity> queryByManagerId(int cmManagerId);
	
	/**
	 * 根据模型ID把模型和相关数据全部删除
	 * @param cmId
	 */
	public void deleteAllByCmId(int cmId);
	
	/**
	 * 根据模型ID把模型和相关数据全部删除
	 * @param cmId集合
	 */
	public void deleteAllByCmId(int[] cmId);
}
