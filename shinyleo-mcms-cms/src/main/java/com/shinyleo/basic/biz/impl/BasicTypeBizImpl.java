package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IBasicTypeBiz;
import com.shinyleo.basic.dao.IBasicTypeDao;
import com.shinyleo.basic.entity.BasicTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("basicTypeBiz")
public class BasicTypeBizImpl extends BaseBizImpl implements IBasicTypeBiz {

    @Autowired
    private IBasicTypeDao basicTypeDao;

    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return basicTypeDao;
    }

    @Override
    public void delete(int basicId, int basicTypeId) {
        // TODO Auto-generated method stub
        basicTypeDao.delete(basicId, basicTypeId);
    }

    @Override
    public void deleteByBasicId(int basicId) {
        // TODO Auto-generated method stub
        basicTypeDao.deleteByBasicId(basicId);
    }

    @Override
    public BasicTypeEntity getByBasicIdAndBasicTypeId(int basicId, int basicTypeId) {
        // TODO Auto-generated method stub
        return basicTypeDao.getByBasicIdAndBasicTypeId(basicId, basicTypeId);
    }

    @Override
    public List<BasicTypeEntity> queryByBasicId(int basicId) {
        // TODO Auto-generated method stub
        return basicTypeDao.queryByBasicId(basicId);
    }


}
