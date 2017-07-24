package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.entity.ManagerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IManagerDao extends IBaseDao {


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
     * @param pageNo 当前页数
     * @param pageSize 显示条数
     * @param orderBy 排序字段
     * @param order 排序方式,true:asc;fales:desc
     * @return 返回角色集合
     */
    public List<BaseEntity> queryByPage(@Param("managerId") int managerId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("orderBy") String orderBy, @Param("order") boolean order);


}
