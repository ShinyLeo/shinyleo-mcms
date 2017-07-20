package com.shinyleo.base.biz.impl;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.base.constant.e.TableEnum;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.util.PageUtil;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhugh on 17/7/20.
 */
public abstract class BaseBizImpl<D extends Serializable> implements IBaseBiz {

    private IBaseDao<D> baseDao;

    protected final Logger LOG = Logger.getLogger(this.getClass());

    @Override
    public int saveEntity(BaseEntity entity) {
        return getDao().saveEntity(entity);
    }

    @Override
    public void deleteEntity(int id) {
        // TODO Auto-generated method stub
        getDao().deleteEntity(id);
    }

    @Override
    public void updateEntity(BaseEntity entity) {

        // TODO Auto-generated method stub
        getDao().updateEntity(entity);
    }

    @Override
    public List<BaseEntity> queryAll() {
        // TODO Auto-generated method stub
        return getDao().queryAll();
    }

    @Override
    public List<BaseEntity> queryByPage(PageUtil page, String orderBy, boolean order) {
        // TODO Auto-generated method stub
        return getDao().queryByPage(page.getPageNo(), page.getPageSize(), orderBy, order);
    }

    @Override
    public List<BaseEntity> query() {
        // TODO Auto-generated method stub
        return getDao().query(null);
    }

    @Override
    public int queryCount() {
        return getDao().queryCount();
    }

    @Override
    public BaseEntity getEntity(int id) {
        return getDao().getEntity(id);
    }

    @Override
    public List queryBySQL(String table, List<String> fields, Map wheres, Integer begin, Integer end) {
        // TODO Auto-generated method stub
        return getDao().queryBySQL(table, fields, wheres, begin, end, null);
    }

    @Override
    public int countBySQL(String table, Map wheres) {
        // TODO Auto-generated method stub
        return getDao().countBySQL(table, wheres);
    }

    @Override
    public List queryBySQL(String table, List<String> fields, Map wheres) {
        // TODO Auto-generated method stub
        return getDao().queryBySQL(table, fields, wheres, null, null, null);
    }

    @Override
    public void updateBySQL(String table, Map fields, Map wheres) {
        // TODO Auto-generated method stub
        getDao().updateBySQL(table, fields, wheres);
    }

    @Override
    public void deleteBySQL(String table, Map wheres) {
        // TODO Auto-generated method stub
        getDao().deleteBySQL(table, wheres);
    }

    @Override
    public void insertBySQL(String table, Map fields) {
        // TODO Auto-generated method stub
        getDao().insertBySQL(table, fields);
    }

    @Override
    public void createTable(String table, Map<Object, List> fileds) {
        // TODO Auto-generated method stub
        getDao().createTable(table, fileds);
    }

    @Override
    public void alterTable(String table, Map fileds, String type) {
        // TODO Auto-generated method stub
        getDao().alterTable(table, fileds, type);
    }

    public void alterTable(String table, Map fileds, TableEnum type) {
        // TODO Auto-generated method stub
        getDao().alterTable(table, fileds, type.toString());
    }

    @Override
    public void dropTable(String table) {
        // TODO Auto-generated method stub
        getDao().dropTable(table);
    }

    @Override
    public void excuteSql(String sql) {
        // TODO Auto-generated method stub
        getDao().excuteSql(sql);
    }

    /**
     * 不需要重写此方法，自动会
     *
     * @return
     */
    protected abstract IBaseDao getDao();

    @Override
    public void saveBatch(List list) {
        getDao().saveBatch(list);
    }

    @Override
    public void delete(int[] ids) {
        getDao().delete(ids);
    }

    @Override
    public void deleteEntity(BaseEntity entity) {
        // TODO Auto-generated method stub
        getDao().deleteByEntity(entity);
    }

    @Override
    public BaseEntity getEntity(BaseEntity entity) {
        // TODO Auto-generated method stub
        return getDao().getByEntity(entity);
    }

    @Override
    public List<BaseEntity> query(BaseEntity entity) {
        // TODO Auto-generated method stub
        return getDao().query(entity);
    }
}
