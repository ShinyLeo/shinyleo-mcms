package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.entity.BasicLogEntity;
import com.shinyleo.util.PageUtil;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicLogBiz  extends IBaseBiz {

    /**
     * 分页查询
     * @return 返回list数组
     */
    List<BaseEntity> query(BasicLogEntity basicLog, PageUtil page, String orderBy, boolean order);

    /**
     * 查询数据表中记录集合总数
     * @return 返回查询总条数
     */
    int count( BasicLogEntity basicLog);
}
