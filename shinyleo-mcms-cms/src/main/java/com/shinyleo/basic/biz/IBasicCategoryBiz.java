package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.basic.entity.BasicCategoryEntity;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 * 类别基础信息关联业务层接口
 */
public interface IBasicCategoryBiz extends IBaseBiz {

    /**
     * 批量更新基础分类关联表
     * @param basicCategoryList 基础分类列表实体
     */
    void updateBatch(List<BasicCategoryEntity> basicCategoryList);

    /**
     * 根据baiscId查询基础分类关联信息
     * @param bcBasicId 基础basicId
     * @return 返回基础分类关联列表信息
     */
    List<BasicCategoryEntity> queryByBasicId(int bcBasicId);

    /**
     * 根据分类id集合去查询符合条件的basicId集合
     * @param categoryIds 分类id集合
     * @return 返回basicId集合
     */
    List<Integer> queryBasicIdsByCategoryId(int[] categoryIds);
}
