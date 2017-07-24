package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.basic.entity.BasicEntity;
import com.shinyleo.util.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicBiz extends IBaseBiz {

    /**
     * 根据分类统计总数
     * @param categoryId　分类编号
     * @return　返回统计总数
     */
    int count(int categoryId);

    /**
     * 根据分类与关键子统计总数
     * @param categoryId　分类编号
     * @param keyWord  关键字
     * @return 返回统计总数
     */
    int count(int categoryId, String keyWord);

    /**
     * 批量级联删除
     * @param basicIds 基本信息实体basicIds集合
     */
    void deleteBasic(int[] basicIds);

    /**
     * 级联删除
     * @param basicId 信息编号
     */
    void deleteBasic(int basicId);

    /**
     * 获取基本信息实
     * @param basicId 信息编号
     * @return 返回基本信息实体
     */
    BasicEntity getBasicEntity(int basicId);


    /**
     * 获取当前文章basicId的上一篇与下一篇
     * @param basicId 当前文章
     * @return  返回信息实体集合 list.get(0):上一篇list.get(1): 下一篇
     */
    List<BasicEntity> getPreviousAndNext(int basicId);

    /**
     * 根据分类查询
     * @param categoryId 　分类编号
     * @return 返回信息实体集合
     */
    List<BasicEntity> query(int categoryId);


    /**
     * 根据分类与关键子统计总数
     * @param categoryId　分类编号
     * @param keyWord　关键字
     * @return　返回信息实体集合
     */
    List<BasicEntity> query(int categoryId, String keyWord);

    /**
     * 根据分类与关键子统计总数
     * @param appId　应用编号
     * @param categoryId　分类编号
     * @param keyWord　关键字
     * @param page　分页
     * @param modelId　模块编号
     *  @param where　条件
     * @return 返回信息实体集合
     */
    List<BasicEntity> query(Integer appId, Integer categoryId, String keyWord, PageUtil page, Integer modelId, Map where);


    /**
     * 保存基本信息实体
     * @param basic basic实体
     * @return 返回编号，确认是否保存
     */
    int saveBasic(BasicEntity basic);

    /**
     * 更新基本信息实体
     * @param basic basic实体
     */
    void updateBasic(BasicEntity basic);

    /**
     * 根据点击次数
     * @param basicId 信息编号
     * @param num null:为递增
     */
    void updateHit(int basicId, Integer num);
}
