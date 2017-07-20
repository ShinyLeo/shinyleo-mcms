package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.entity.BasicEntity;
import com.shinyleo.basic.entity.ColumnEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IColumnDao  extends IBaseDao {


    /**
     * 根据站点ID查询该站点下的栏目集合
     * @param columnWebsiteId 站点Id
     * @return 栏目集合
     */
    public List<ColumnEntity> queryColumnListByWebsiteId(@Param("columnWebsiteId")int columnWebsiteId);

    /**
     * 根据站点Id查询该站点下的栏目的父栏目Id为categoryCategoryId的子栏目集合
     * @param categoryCategoryId 父栏目ID
     * @param columnWebsiteId 站点Id
     * @return 栏目集合
     */
    public List<ColumnEntity> queryColumnByCategoryIdAndWebsiteIdAndModelId(@Param("categoryCategoryId")int categoryCategoryId,@Param("columnWebsiteId")int columnWebsiteId,@Param("modelId")Integer modelId,@Param("size")Integer size);

    /**
     * 根据栏目ID查询其子栏目ID集合
     * @param categoryId 栏目ID
     * @param appId 栏目ID
     * @return 子栏目ID集合
     */
    public List<Integer> queryColumnChildIdList(@Param("categoryId")int categoryId,@Param("appId")int appId);

    /**
     * 根据栏目属性和栏目模块编码查询栏目站点id为columnWebsiteId的栏目集合
     * @param columnType 栏目属性
     * @param columnWebsiteId 栏目所属站点ID
     * @return 栏目对象
     */
    public List <ColumnEntity> queryColumnListBycolumnType(@Param("columnType")int columnType,@Param("columnWebsiteId")int columnWebsiteId);

    /**
     * 根据站点Id查询该站点下的栏目的父栏目Id为categoryCategoryId的子栏目集合数目统计
     * @param categoryCategoryId 父栏目ID
     * @param columnWebsiteId 站点ID
     * @return 子栏目统计数目
     */
    public int queryColumnChildListCountByWebsiteId(@Param("categoryCategoryId")int categoryCategoryId,@Param("columnWebsiteId")int columnWebsiteId);


    /**
     * 通过管理员ID和模块ID查询订单类型集合
     * @param categoryManagerId 管理员ID
     * @param categoryModelId 模块ID
     * @return 返回订单集合
     */
    public List<BasicEntity> queryCategoryByManagerIdAndModelId(@Param("categoryManagerId")int categoryManagerId, @Param("categoryModelId")int categoryModelId);

    /**
     * 获取当前应用下面对应模块的所以栏目分类
     * @param modelId 模块信息
     * @return 记录集合
     */
    public List<ColumnEntity> queryByAppIdAndModelId(@Param("appId")int appId, @Param("modelId")int modelId);

}
