package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.biz.IManagerBiz;
import com.shinyleo.basic.dao.IManagerDao;
import com.shinyleo.basic.entity.ManagerEntity;
import com.shinyleo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("managerBiz")
public class ManagerBizImpl extends BaseBizImpl implements IManagerBiz {


    /**
     * 注入管理员持久化层
     */
    private IManagerDao managerDao;

    /**
     * 获取管理员持久化层
     * @return managerDao 返回管理员持久化层
     */
    public IManagerDao getManagerDao() {
        return managerDao;
    }

    /**
     * 设置managerDao
     * @param managerDao 管理员持久化层
     */
    @Autowired
    public void setManagerDao(IManagerDao managerDao) {
        // TODO Auto-generated method stub
        this.managerDao = managerDao;
    }

    /**
     * 获取管理员持久化层
     * @return managerDao 返回管理员持久化层
     */
    @Override
    public IBaseDao getDao() {
        // TODO Auto-generated method stub
        return managerDao;
    }

    @Override
    public ManagerEntity queryManagerByManagerName(String managerName) {
        // TODO Auto-generated method stub
        return managerDao.queryManagerByManagerName(managerName);
    }

    @Override
    public void updateUserPasswordByUserName(ManagerEntity manager) {
        // TODO Auto-generated method stub
        managerDao.updateUserPasswordByUserName(manager);
    }

    @Override
    public int countManagerName(String managerName){
        // TODO Auto-generated method stub
        return managerDao.countManagerName(managerName);
    }

    @Override
    public List<BaseEntity> queryAllChildManager(int managerId){
        // TODO Auto-generated method stub
        return managerDao.queryAllChildManager(managerId);
    }

    @Override
    public void deleteManagerByRoleId(int managerRoleID){
        // TODO Auto-generated method stub
        managerDao.deleteManagerByRoleId(managerRoleID);
    }

    @Override
    public List<BaseEntity> queryByPage(int managerId, PageUtil page, String orderBy, boolean order){
        // TODO Auto-generated method stub
        return managerDao.queryByPage(managerId, page.getPageNo(),page.getPageSize(), orderBy, order);
    }
}
