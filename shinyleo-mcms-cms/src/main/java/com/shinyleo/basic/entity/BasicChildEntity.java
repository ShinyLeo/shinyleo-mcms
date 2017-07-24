package com.shinyleo.basic.entity;

import com.shinyleo.base.entity.BaseEntity;

/**
 * Created by shinyleo on 17/7/20.
 *  基础表之间的子父关联关系
 */
public class BasicChildEntity extends BaseEntity {

    /**
     * 基础basicId
     */
    private int basicId;

    /**
     * 基础basicId关联的子id
     */
    private int basicChildId;



    public int getBasicId() {
        return basicId;
    }

    public void setBasicId(int basicId) {
        this.basicId = basicId;
    }

    public int getBasicChildId() {
        return basicChildId;
    }

    public void setBasicChildId(int basicChildId) {
        this.basicChildId = basicChildId;
    }


}
