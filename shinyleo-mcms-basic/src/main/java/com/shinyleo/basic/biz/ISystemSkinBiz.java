package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.basic.entity.SystemSkinEntity;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface ISystemSkinBiz extends IBaseBiz {

    /**
     * 获取管理员对应的管理皮肤
     * @param managerId　管理员编号
     * @return　返回主题实体
     */
    SystemSkinEntity getByManagerId(int managerId);

    /**
     *设置管理员的后台皮肤
     * @param managerId　管理员编号
     * @param systemSkinId　皮肤编号
     */
    SystemSkinEntity updateManagerSystemSkin(int managerId,int systemSkinId);

}
