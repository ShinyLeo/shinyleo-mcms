package com.shinyleo.basic.entity;

import com.shinyleo.base.entity.BaseEntity;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public class ManagerSessionEntity extends ManagerEntity {

    /**
     * 父管理员ID
     */
    private int managerParentID;

    /**
     * 子管理员集合
     */
    private List<BaseEntity> managerChildIDs;

    /**
     * 站点ID
     */
    private int basicId;

    /**
     * 当前应用使用的默认风格
     */
    private String style;


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * 管理员的后台皮肤
     */
    private SystemSkinEntity systemSkin;

    /**
     * 获取managerParentID
     * @return managerParentID
     */
    public int getManagerParentID() {
        return managerParentID;
    }

    /**
     * 设置managerParentID
     * @param managerParentID
     */
    public void setManagerParentID(int managerParentID) {
        this.managerParentID = managerParentID;
    }

    /**
     * 获取managerChildIDs
     * @return managerChildIDs
     */
    public List<BaseEntity> getManagerChildIDs() {
        return managerChildIDs;
    }

    /**
     * 设置managerChildIDs
     * @param managerChildIDs
     */
    public void setManagerChildIDs(List<BaseEntity> managerChildIDs) {
        this.managerChildIDs = managerChildIDs;
    }

    /**
     * 获取basicId
     * @return basicId
     */
    public int getBasicId() {
        return basicId;
    }

    /**
     * 设置basicId
     * @param basicId
     */
    public void setBasicId(int basicId) {
        this.basicId = basicId;
    }

    public SystemSkinEntity getSystemSkin() {
        return systemSkin;
    }

    public void setSystemSkin(SystemSkinEntity systemSkin) {
        this.systemSkin = systemSkin;
    }
}
