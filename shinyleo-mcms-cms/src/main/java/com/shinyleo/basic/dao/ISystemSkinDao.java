package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.entity.SystemSkinEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface ISystemSkinDao extends IBaseDao {

    /**
     * 获取管理员对应的管理皮肤
     * @param managerId　管理员编号
     * @return　返回主题实体
     */
    public SystemSkinEntity getByManagerId(@Param("managerId") int managerId);

    /**
     *更新管理员的后台皮肤
     * @param managerId　管理员编号
     * @param systemSkinId　皮肤编号
     */
    public void updateManagerSystemSkin(@Param("managerId") int managerId, @Param("systemSkinId") int systemSkinId);

    /**
     *保存管理员的后台皮肤
     * @param managerId　管理员编号
     * @param systemSkinId　皮肤编号
     */
    public void saveManagerSystemSkin(@Param("managerId") int managerId, @Param("systemSkinId") int systemSkinId);
}
