package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.entity.BasicEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicDao extends IBaseDao {

    /**
     * 更新点击次数
     *
     * @param basicId 文章编号
     * @param num null:为递增
     */
    void updateHit(@Param("basicId") int basicId, @Param("num") Integer num);

    /**
     * 根据分类与关键子统计总数
     *
     * @param categoryId
     *            　分类编号
     * @param keyWord
     *            关键字
     * @return　返回总数
     */
    int count(@Param("categoryId") int categoryId, @Param("keyWord") String keyWord);

    /**
     * 根据分类与关键子统计总数
     * @param appId 站点ID
     * @param categoryId
     *            　分类编号
     * @param keyWord
     *            　关键字
     * @param begin 开始
     * @param end　结束
     * @param orderField　排序字段
     * @param ad　排序方式true:升序 false:倒序
     * @param modelId 模块ID
     * @param where 查询条件
     * @return　返回列表实体
     */
    List<BasicEntity> query(@Param("appId") Integer appId, @Param("categoryId") Integer categoryId, @Param("keyWord") String keyWord, @Param("begin") Integer begin, @Param("end") Integer end, @Param("orderField") String orderField, @Param("ad") Boolean ad, @Param("modelId") Integer modelId, @Param("where") Map where);

    /**
     * 获取当前文章basicId的上一篇与下一篇
     * @param basicId 当前文章
     * @return 返回列表实体，list.get(0):上一篇list.get(1): 下一篇
     */
    List<BasicEntity> getPreviousAndNext(int basicId);

}
