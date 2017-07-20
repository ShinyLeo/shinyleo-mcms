package com.shinyleo.basic.entity;

import com.shinyleo.base.entity.BaseEntity;

/**
 * Created by shinyleo on 17/7/20.
 * 类别基础信息关联表
 */
public class BasicCategoryEntity  extends BaseEntity {

    /**
     * 类别编号
     */
    private int bcCategoryId;

    /**
     * 基本信息编号
     */
    private int bcBasicId;

    /**
     *获取 bcCategoryId
     * @return bcCategoryId
     */
    public int getBcCategoryId() {
        return bcCategoryId;
    }

    /**
     *设置bcCategoryId
     * @param bcCategoryId
     */
    public void setBcCategoryId(int bcCategoryId) {
        this.bcCategoryId = bcCategoryId;
    }

    /**
     *获取 bcBasicId
     * @return bcBasicId
     */
    public int getBcBasicId() {
        return bcBasicId;
    }

    /**
     *设置bcBasicId
     * @param bcBasicId
     */
    public void setBcBasicId(int bcBasicId) {
        this.bcBasicId = bcBasicId;
    }
}
