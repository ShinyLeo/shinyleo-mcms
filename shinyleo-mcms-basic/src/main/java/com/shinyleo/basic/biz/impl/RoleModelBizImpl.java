package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IRoleModelBiz;
import com.shinyleo.basic.dao.IRoleModelDao;
import com.shinyleo.basic.entity.RoleModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("roleModelBiz")
public class RoleModelBizImpl extends BaseBizImpl implements IRoleModelBiz {

    /**
     * 角色模块关联持久化层
     */
    @Autowired
    private IRoleModelDao roleModelDao;

    /**
     * 获取角色模块持久化层
     * @return roleModelDao 返回角色模块持久化层
     */
    @Override
    public IBaseDao getDao() {
        // TODO Auto-generated method stub
        return roleModelDao;
    }

    @Override
    public void saveEntity(List<RoleModelEntity> roleModelList){
        // TODO Auto-generated method stub
        roleModelDao.saveEntity(roleModelList);
    }

    @Override
    public void updateEntity(List<RoleModelEntity> roleModelList){
        // TODO Auto-generated method stub
        roleModelDao.updateEntity(roleModelList);
    }

    @Override
    public List<RoleModelEntity> queryByRoleId(int roleId) {
        // TODO Auto-generated method stub
        return roleModelDao.queryByRoleId(roleId);
    }
}
