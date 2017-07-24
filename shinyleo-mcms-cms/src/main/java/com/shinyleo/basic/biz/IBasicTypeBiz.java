package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.basic.entity.BasicTypeEntity;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicTypeBiz  extends IBaseBiz {

    /**
     * 删除基础信息对应属性
     *
     * @param basicId
     *            基础信息编号
     * @param basicTypeId
     *            基础信息类型编号
     */
    void delete(int basicId, int basicTypeId);

    /**
     * 删除基础信息关联属性
     *
     * @param basicId
     *            基础信息编号
     */
    void deleteByBasicId(int basicId);

    /**
     * 获取关联属性
     *
     * @param basicId
     *            信息编号
     * @param basicTypeId
     *            信息属性
     * @return
     */
    BasicTypeEntity getByBasicIdAndBasicTypeId(int basicId, int basicTypeId);

    /**
     * 查询基础信息关联属性
     *
     * @param basicId
     *            信息编号
     * @return
     */
    List<BasicTypeEntity> queryByBasicId(int basicId);
}
