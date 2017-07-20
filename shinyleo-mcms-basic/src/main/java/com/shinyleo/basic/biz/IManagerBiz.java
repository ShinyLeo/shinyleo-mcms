package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.entity.ManagerEntity;
import com.shinyleo.util.PageUtil;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 * 管理员业务层，接口，继承IBaseBiz
 */
public interface IManagerBiz extends IBaseBiz {

    /**
     * 通过账号查询管理员，主要用于登陆模块
     * @param managerName 帐号
     * @return 返回管理员实体
     */
    public ManagerEntity queryManagerByManagerName(String managerName);

    /**
     * 根据用户名修改用户密码
     * @param manager 管理员实体
     */
    public void updateUserPasswordByUserName(ManagerEntity manager);

    /**
     * 统计该管理员帐号在数据库中的存在数
     * @param managerName 管理员帐号
     * @return 返回存在数量
     */
    public int countManagerName(String managerName);

    /**
     * 查询当前登录的管理员的所有子管理员
     * @return 返回管理员集合
     */
    public List<BaseEntity> queryAllChildManager(int managerId);

    /**
     * 通过角色ID删除管理员实体
     * @param managerRoleID 角色ID
     */
    public void deleteManagerByRoleId(int managerRoleID);

    /**
     * 分页查询当前管理员所创建的角色
     * @param managerId 管理员ID
     * @param page Map对象
     * @param orderBy 排序字段
     * @param order 排序方式,true:asc;fales:desc
     * @return 返回角色实体集合
     */
    public List<BaseEntity> queryByPage(int managerId, PageUtil page, String orderBy, boolean order);

}
