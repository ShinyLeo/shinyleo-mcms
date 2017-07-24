package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.entity.RoleModelEntity;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 *
 * 角色模块关联持久化层，接口，
 */
public interface IRoleModelDao  extends IBaseDao {

    /**
     * 保存该角色对应的模块集合
     * @param roleModelList 集合
     */
    public void saveEntity(List<RoleModelEntity> roleModelList);

    /**
     * 更新该角色对应的模块集合
     * @param roleModelList 集合
     */
    public void updateEntity(List<RoleModelEntity> roleModelList);

    /**
     * 根据角色编号删除对应功能
     * @param id 角色编号
     */
    public void deleteByRoleId(int id);

    /**
     * 通过角色获取所有关联的模块id
     * @param roleId
     */
    public List<RoleModelEntity> queryByRoleId(int roleId);
}
