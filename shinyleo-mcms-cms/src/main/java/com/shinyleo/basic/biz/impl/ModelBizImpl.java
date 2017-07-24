package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.constant.e.BaseEnum;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.biz.IModelBiz;
import com.shinyleo.basic.constant.e.ModelEnum;
import com.shinyleo.basic.dao.IModelDao;
import com.shinyleo.basic.entity.ModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("modelBiz")
public class ModelBizImpl extends BaseBizImpl implements IModelBiz {


    @Override
    public List<BaseEntity> queryChildList(int modelModelId) {
        // TODO Auto-generated method stub
        return modelDao.queryChildList(modelModelId);
    }

    @Override
    public List<BaseEntity> queryParent() {
        // TODO Auto-generated method stub
        return modelDao.queryParent();
    }

    @Override
    public List<BaseEntity> queryModelByManagerId(int managerId, int modelId){
        // TODO Auto-generated method stub
        return modelDao.queryModelByManagerId(managerId,modelId);
    }

    @Override
    public List<BaseEntity> queryModelByManager() {
        // TODO Auto-generated method stub
        return modelDao.queryModelByManager();
    }

    @Override
    public List<BaseEntity> queryModelByRoleId(int roleId){
        // TODO Auto-generated method stub
        return modelDao.queryModelByRoleId(roleId);
    }

    @Override
    public ModelEntity getEntityByModelCode(BaseEnum modelCode){
        // TODO Auto-generated method stub
        return modelDao.getEntityByModelCode(modelCode.toString());
    }

    @Override
    public ModelEntity getEntityByModelCode(String modelCode) {
        // TODO Auto-generated method stub
        return modelDao.getEntityByModelCode(modelCode);
    }

    /**
     * 模块持久化层
     */
    private IModelDao modelDao;


    /**
     * 获取模块持久化层
     * @return modelDao 返回模块持久化层
     */
    public IModelDao getModelDao() {
        return modelDao;
    }

    @Autowired
    public void setModelDao(IModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return modelDao;
    }

    @Override
    public ModelEntity getModel(String modelType, int modelId) {
        // TODO Auto-generated method stub
        return modelDao.getModel(modelType,modelId);
    }

    @Override
    public List<BaseEntity> queryModelByIsMenu(ModelEnum modelEnum) {
        // TODO Auto-generated method stub
        return modelDao.queryModelByIsMenu(modelEnum.toInt());
    }

}
