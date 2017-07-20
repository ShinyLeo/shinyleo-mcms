package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IBasicBiz;
import com.shinyleo.basic.dao.IBasicDao;
import com.shinyleo.basic.entity.BasicEntity;
import com.shinyleo.util.PageUtil;
import net.shinyleo.basic.util.BasicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */


@Service("basicBiz")
public class BasicBizImpl  extends BaseBizImpl implements IBasicBiz {


    /**
     * 注入基本信息持久化层
     */
    @Autowired
    private IBasicDao basicDao;

    @Override
    public int count(int categoryId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int count(int categoryId, String keyWord) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteBasic(int[] basicIds) {
        // TODO Auto-generated method stub
        basicDao.delete(basicIds);
        delete(basicIds);
    }

    @Override
    public void deleteBasic(int basicId) {
        basicDao.deleteEntity(basicId);
        deleteEntity(basicId);
    }

    @Override
    public BasicEntity getBasicEntity(int basicId){
        return (BasicEntity) basicDao.getEntity(basicId);
    }

    /**
     * 获取基本信息持久化层
     * @return basicDao 返回基本信息持久化层
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return basicDao;
    }

    @Override
    public List<BasicEntity> getPreviousAndNext(int basicId) {
        // TODO Auto-generated method stub
        return basicDao.getPreviousAndNext(basicId);
    }

    @Override
    public List<BasicEntity> query(int categoryId) {
        // TODO Auto-generated method stub
        return basicDao.query(null,categoryId, null, null, null, null, null,null,null);
    }

    @Override
    public List<BasicEntity> query(int categoryId, String keyWord) {
        // TODO Auto-generated method stub
        return basicDao.query(null,categoryId, keyWord, null, null, null, null,null,null);
    }

    @Override
    public List<BasicEntity> query(Integer appId, Integer categoryId, String keyWord, PageUtil page, Integer modelId, Map where) {
        // TODO Auto-generated method stub
        if (page==null) {
            return basicDao.query(appId,categoryId, keyWord, null, null, null, null,modelId,where);
        }
        return basicDao.query(appId,categoryId, keyWord, page.getPageSize()*page.getPageNo(), page.getPageSize(), null, null,modelId,where);
    }

    @Override
    public int saveBasic(BasicEntity basic) {
        basic.setBasicAppId(BasicUtil.getAppId());
        basicDao.saveEntity(basic);
        return saveEntity(basic);
    }

    @Override
    public void updateBasic(BasicEntity basic) {
        basicDao.updateEntity(basic);
        updateEntity(basic);
    }

    @Override
    public void updateHit(int basicId, Integer num) {
        // TODO Auto-generated method stub
        basicDao.updateHit(basicId, num);
    }

}
