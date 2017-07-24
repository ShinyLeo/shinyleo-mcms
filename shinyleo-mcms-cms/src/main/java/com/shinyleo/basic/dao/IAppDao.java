package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IAppDao extends IBaseDao {

    /**
     * 根据域名查找相同域名的个数
     * @param websiteUrl 域名
     * @return 返回相同域名的个数
     */
    int countByUrl(String websiteUrl);


    /**
     * 根据域名查找站点实体
     * @param websiteUrl 域名
     * @return 返回站点实体
     */
    BaseEntity getByUrl(String websiteUrl);

    /**
     * 更据站点管理员id查找站点
     * @param managerId 管理员id
     * @return 返回站点实体
     */
    BaseEntity getByManagerId(int managerId);
}
