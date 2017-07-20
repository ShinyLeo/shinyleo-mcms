package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IBasicCategoryBiz;
import com.shinyleo.basic.dao.IBasicCategoryDao;
import com.shinyleo.basic.entity.BasicCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("basicCategoryBizImpl")
public class BasicCategoryBizImpl extends BaseBizImpl implements IBasicCategoryBiz {
    /**
     * 注入分类关联持久化层
     */
    @Autowired
    private IBasicCategoryDao basicCategoryDao;


    @Override
    public void updateBatch(List<BasicCategoryEntity> basicCategoryList) {
        // TODO Auto-generated method stub
        basicCategoryDao.updateBatch(basicCategoryList);
    }

    /**
     * 获取分类关联持久化层
     * @return basicCategoryDao 返回分类关联持久化层
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return basicCategoryDao;
    }

    @Override
    public List<BasicCategoryEntity> queryByBasicId(int bcBasicId) {
        // TODO Auto-generated method stub
        return basicCategoryDao.queryByBasicId(bcBasicId);
    }

    @Override
    public List<Integer> queryBasicIdsByCategoryId(int[] categoryIds) {
        // TODO Auto-generated method stub
        if(categoryIds==null || categoryIds.length<1){
            return null;
        }
        return basicCategoryDao.queryBasicIdsByCategoryId(categoryIds,categoryIds.length);
    }

}
