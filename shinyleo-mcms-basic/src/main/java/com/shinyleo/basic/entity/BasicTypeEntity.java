package com.shinyleo.basic.entity;

import com.shinyleo.base.entity.BaseEntity;

/**
 * Created by shinyleo on 17/7/20.
 */
public class BasicTypeEntity extends BaseEntity {

    /**
     * 关联basicId
     */
    private int basicTypeBasicId;

    /**
     * 基础信息属性
     */
    private int basicTypeType;


    public BasicTypeEntity(int basicId,int type) {
        this.basicTypeBasicId = basicId;
        this.basicTypeType = type;
    }


    public int getBasicTypeBasicId() {
        return basicTypeBasicId;
    }


    public void setBasicTypeBasicId(int basicTypeBasicId) {
        this.basicTypeBasicId = basicTypeBasicId;
    }


    public int getBasicTypeType() {
        return basicTypeType;
    }


    public void setBasicTypeType(int basicTypeType) {
        this.basicTypeType = basicTypeType;
    }
}
