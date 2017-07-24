package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.entity.BasicLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicLogDao extends IBaseDao {

    /**
     * 分页查询
     * @param pageNo 页码
     * @param pageSize 显示条数
     * @param orderBy 排序字段
     * @param order order 排序方式,true:asc;fales:desc
     * @return 返回list数组
     */
    List<BaseEntity> query(@Param("basicLog") BasicLogEntity basicLog, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("orderBy") String orderBy, @Param("order") boolean order);

    /**
     * 查询数据表中记录集合总数
     * @return 返回查询总条数
     */
    int count(@Param("basicLog") BasicLogEntity basicLog);
}
