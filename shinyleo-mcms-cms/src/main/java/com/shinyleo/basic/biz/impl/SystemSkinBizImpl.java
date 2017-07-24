package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.ISystemSkinBiz;
import com.shinyleo.basic.dao.ISystemSkinDao;
import com.shinyleo.basic.entity.SystemSkinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("systemSkinBiz")
public class SystemSkinBizImpl extends BaseBizImpl implements ISystemSkinBiz {

    /**
     * 系统主题持久化层
     */
    @Autowired
    private ISystemSkinDao systemSkinDao;

    @Override
    public SystemSkinEntity getByManagerId(int managerId) {
        // TODO Auto-generated method stub
        return systemSkinDao.getByManagerId(managerId);
    }

    @Override
    public SystemSkinEntity updateManagerSystemSkin(int managerId, int systemSkinId) {
        // TODO Auto-generated method stub
        SystemSkinEntity sse = systemSkinDao.getByManagerId(managerId);
        if (sse!=null) {
            systemSkinDao.updateManagerSystemSkin(managerId, systemSkinId);
        } else {
            systemSkinDao.saveManagerSystemSkin(managerId, systemSkinId);
        }
        return (SystemSkinEntity)systemSkinDao.getEntity(systemSkinId);

    }

    /**
     * 获取系统主题持久化层
     * @return systemSkinDao 返回系统主题持久化层
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return systemSkinDao;
    }
}
