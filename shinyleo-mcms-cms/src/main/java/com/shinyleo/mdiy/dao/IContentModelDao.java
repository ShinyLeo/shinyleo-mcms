package com.shinyleo.mdiy.dao;


import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public interface IContentModelDao extends IBaseDao {
	
	/**
	 * 根据内容模型的表名查找实体
	 * @param cmTableName 内容模型表名
	 */
	public BaseEntity getContentModelByTableName(String cmTableName);
	
	/**
	 * 根据管理员Id查找内容模型的总数
	 * @param cmManagerId 管理员id
	 * @return 内容模型总数
	 */
	public int getContentModelByManagerId(int cmManagerId);
	
	/**
	 * 根据管理员id查找内容模型实体
	 * @param cmManagerId 管理员id
	 * @return 内容模型列表集合
	 */
	public List<BaseEntity> queryByManagerId(int cmManagerId);
	
	/**
	 * 分页查询指定管理员的表单内容模型
	 * @param order 排序方式,true:asc;fales:desc
	 * @param cmManagerId 管理员id
	 * @return 内容模型列表集合
	 */
	List<BaseEntity> queryPageByManagerId(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("orderBy") String orderBy, @Param("order") boolean order, @Param("cmManagerId") int cmManagerId);
}
