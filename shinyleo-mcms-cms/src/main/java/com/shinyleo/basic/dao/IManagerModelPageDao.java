package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.entity.ManagerModelPageEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IManagerModelPageDao extends IBaseDao {
    /**
     * 根据模块id和管理员id查找实体信息
     * @param managerModelPagemanagerId 管理员id
     * @param managerModelPageModelId 模块id
     * @return 返回管理员实体
     */
    ManagerModelPageEntity getByManagerIdAndModelId(@Param("managerModelPagemanagerId") int managerModelPagemanagerId, @Param("managerModelPageModelId") int managerModelPageModelId);
}
