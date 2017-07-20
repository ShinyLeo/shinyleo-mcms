package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IManagerModelPageBiz;
import com.shinyleo.basic.dao.IManagerModelPageDao;
import com.shinyleo.basic.entity.ManagerModelPageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service
public class ManagerModelPageBizImpl  extends BasicBizImpl implements IManagerModelPageBiz {

    /**
     * 声明管理员模块页面持久化层
     */
    @Autowired
    private IManagerModelPageDao managerModelPageDao;

    /**
     * 获取管理员模块页面持久化层
     * @return managerModelPageDao 返回管理员模块页面持久化层
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return managerModelPageDao;
    }

    @Override
    public ManagerModelPageEntity getByManagerIdAndModelId(
            int managerModelPagemanagerId, int managerModelPageModelId) {
        // TODO Auto-generated method stub
        return managerModelPageDao.getByManagerIdAndModelId(managerModelPagemanagerId, managerModelPageModelId);
    }

}
