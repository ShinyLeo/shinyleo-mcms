package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IBasicChildBiz;
import com.shinyleo.basic.dao.IBasicChildDao;
import com.shinyleo.basic.entity.BasicChildEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("basicChildBiz")
public class BasicChildBizImpl  extends BaseBizImpl implements IBasicChildBiz {

    /**
     * 基础表之间的子父关联关系持久层
     */
    @Autowired
    private IBasicChildDao basicChildDao;

    @Override
    public void delete(int basicId) {
        // TODO Auto-generated method stub
        basicChildDao.delete(basicId);
    }

    @Override
    public List<BasicChildEntity> queryByBasicId(int basicId) {
        // TODO Auto-generated method stub
        return basicChildDao.queryByBasicId(basicId);
    }

    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return basicChildDao;
    }

    @Override
    public void deleteBatch(int[] basicIds) {
        basicChildDao.deleteBatch(basicIds);

    }



    @Override
    public void deleteByChildIds(int basicId, int[] basicChildIds) {
        basicChildDao.deleteByChildIds(basicId, basicChildIds);

    }

    @Override
    public void deleteByBasicIdAndChildId(int basicId, int basicChildId) {
        basicChildDao.deleteByBasicIdAndChildId(basicId, basicChildId);

    }

}
