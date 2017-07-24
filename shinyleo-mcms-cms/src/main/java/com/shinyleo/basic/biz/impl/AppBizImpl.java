package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IAppBiz;
import com.shinyleo.basic.dao.IAppDao;
import com.shinyleo.basic.entity.AppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shinyleo on 17/7/20.
 * 网站基本信息业务层实现类
 */

@Service("appBiz")
public class AppBizImpl extends BasicBizImpl implements IAppBiz {

    /**
     * 声明IAppDao持久化层
     */
    @Autowired
    private IAppDao appDao;


    @Override
    public AppEntity getByManagerId(int managerId) {
        // TODO Auto-generated method stub
        return (AppEntity) appDao.getByManagerId(managerId);
    }

    /**
     * 获取应用持久化层
     * @return appDao 返回应用持久化层
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return appDao;
    }

    @Override
    public int countByUrl(String websiteUrl) {
        // TODO Auto-generated method stub
        return appDao.countByUrl(websiteUrl);
    }

    @Override
    public AppEntity getByUrl(String url) {
        // TODO Auto-generated method stub
        return (AppEntity) appDao.getByUrl(url);
    }
}
