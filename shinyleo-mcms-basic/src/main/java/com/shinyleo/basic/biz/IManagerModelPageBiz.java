package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.basic.entity.ManagerModelPageEntity;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IManagerModelPageBiz extends IBaseBiz {

    /**
     * 根据模块id和管理员id查找实体信息
     * @param managerModelPagemanagerId 管理员id
     * @param managerModelPageModelId 模块id
     * @return 返回管理员实体
     */
    ManagerModelPageEntity getByManagerIdAndModelId(int managerModelPagemanagerId, int managerModelPageModelId);
}
