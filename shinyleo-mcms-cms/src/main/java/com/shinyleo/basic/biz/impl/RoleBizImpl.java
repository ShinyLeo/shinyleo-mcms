package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.biz.IRoleBiz;
import com.shinyleo.basic.dao.IRoleDao;
import com.shinyleo.basic.entity.RoleEntity;
import com.shinyleo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("roleBiz")
public class RoleBizImpl extends BaseBizImpl implements IRoleBiz {

    /**
     * 注入角色持久化层
     */
    @Autowired
    private IRoleDao roleDao;

    /**
     * 获取角色持久化层
     * @return roleDao 返回角色持久化层
     */
    @Override
    public IBaseDao getDao() {
        return roleDao;
    }

    @Override
    public RoleEntity queryRoleByRoleName(String roleName, int roleManagerId){
        return roleDao.queryRoleByRoleName(roleName, roleManagerId);
    }

    @Override
    public List<BaseEntity> queryRoleByManagerId(int roleManagerId){
        return roleDao.queryRoleByManagerId(roleManagerId);
    }

    @Override
    public int countRoleName(String roleName, int roleManagerId){
        return roleDao.countRoleName(roleName,roleManagerId);
    }

    @Override
    public List<BaseEntity> queryByPage(int roleManagerId, PageUtil page, String orderBy, boolean order){
        return roleDao.queryByPage(roleManagerId, page.getPageNo(),page.getPageSize(), orderBy, order);
    }

    @Override
    public void deleteAll(String[] ids) {
        // TODO Auto-generated method stub
        roleDao.deleteAll(ids);
    }

    @Override
    public int getCountByManagerId(int managerId) {
        // TODO Auto-generated method stub
        return roleDao.getCountByManagerId(managerId);
    }
}
