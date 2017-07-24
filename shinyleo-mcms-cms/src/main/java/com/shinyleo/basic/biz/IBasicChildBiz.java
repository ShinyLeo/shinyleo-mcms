package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.basic.entity.BasicChildEntity;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicChildBiz extends IBaseBiz {


    /**
     * 根据基础表id删除数据
     * @param basicId 基础表id
     */
    void delete(int basicId);

    /**
     * 根据基础表id查询基础表关联数据集合
     * @param basicId  基础表id
     * @return  基础表关联数据集合
     */
    List<BasicChildEntity> queryByBasicId(int basicId);

    /**
     * 根据basicId集合实现批量的删除
     * @param basicIds basicId集合
     */
    void deleteBatch(int[] basicIds);

    /**
     * 根据基础id和子id集合删除
     * @param basicId 基础id
     * @param basicChildIds 子集合id
     */
    void deleteByChildIds(int basicId, int[] basicChildIds);

    /**
     * 根据基础id和子id删除
     * @param basicId  基础id
     * @param basicChildId 子id
     */
    void deleteByBasicIdAndChildId(int basicId, int basicChildId);
}
