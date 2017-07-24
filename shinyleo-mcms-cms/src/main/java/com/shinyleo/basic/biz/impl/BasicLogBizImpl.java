package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.biz.IBasicLogBiz;
import com.shinyleo.basic.dao.IBasicLogDao;
import com.shinyleo.basic.entity.BasicLogEntity;
import com.shinyleo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("basicLogBiz")
public class BasicLogBizImpl extends BaseBizImpl implements IBasicLogBiz {

    /**
     * 声明IAppDao持久化层
     */
    @Autowired
    private IBasicLogDao basicLogDao;

    @Override
    protected IBaseDao getDao() {
        return basicLogDao;
    }

    @Override
    public List<BaseEntity> query(BasicLogEntity basicLog, PageUtil page, String orderBy, boolean order) {
        // TODO Auto-generated method stub
        return basicLogDao.query(basicLog, page.getPageNo(), page.getPageSize(), orderBy, order);
    }

    @Override
    public int count(BasicLogEntity basicLog) {
        // TODO Auto-generated method stub
        return basicLogDao.count(basicLog);
    }

}
