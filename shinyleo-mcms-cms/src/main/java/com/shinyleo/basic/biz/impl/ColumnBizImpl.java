package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.IColumnBiz;
import com.shinyleo.basic.dao.IColumnDao;
import com.shinyleo.basic.entity.BasicEntity;
import com.shinyleo.basic.entity.ColumnEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("columnBiz")
public class ColumnBizImpl  extends CategoryBizImpl implements IColumnBiz {
    /**
     * 栏目持久化层注入
     */
    private IColumnDao columnDao;

    /**
     * 获取 columnDao
     *
     * @return columnDao
     */
    public IColumnDao getColumnDao() {
        return columnDao;
    }

    /**
     * 设置 columnDao
     *
     * @param columnDao
     */
    @Autowired
    public void setColumnDao(IColumnDao columnDao) {
        this.columnDao = columnDao;
    }

    @Override
    protected IBaseDao getDao() {
        return columnDao;
    }

    /**
     * 根据站点ID查询该站点下的栏目集合
     *
     * @param columnWebsiteId
     *            站点Id
     * @return 栏目集合
     */
    public List<ColumnEntity> queryColumnListByWebsiteId(int columnWebsiteId) {
        return columnDao.queryColumnListByWebsiteId(columnWebsiteId);
    }

    public List<ColumnEntity> queryChild(int categoryCategoryId, int columnWebsiteId, Integer modelId, Integer size) {
        return columnDao.queryColumnByCategoryIdAndWebsiteIdAndModelId(categoryCategoryId, columnWebsiteId, modelId,
                size);
    }

    public List<ColumnEntity> queryAll(int appId, int modelId) {
        return columnDao.queryByAppIdAndModelId(appId, modelId);
    }

    /**
     * 通过站点Id查询栏目的同级栏目和他的父级栏目（包括他的间接父栏目直到顶级栏目）的同级栏目集合
     *
     * @param categoryId
     *            栏目ID
     * @param columnWebsiteId
     *            站点Id
     * @return 栏目集合
     */
    public List<ColumnEntity> queryColumnChildListRecursionByWebsiteId(int categoryId, int columnWebsiteId) {

        List<ColumnEntity> list = new ArrayList<ColumnEntity>();
        ColumnEntity columnEntity = (ColumnEntity) (columnDao.getEntity(categoryId));
        if (columnEntity != null) {
            int categoryCategoryId = columnEntity.getCategoryCategoryId();
            queryExpansionColumnListByWebsiteId(categoryCategoryId, list, columnWebsiteId);
        }

        return list;
    }

    /**
     * 通过栏目的站点ID查询该站点下的栏目的父栏目Id为categoryCategoryId子栏目
     * 通过递归查询将父栏目ID为categoryCategoryId的子栏目集合和他对应在同一节点树的父级栏目的集合全部查询装入List中
     *
     * @param categoryCategoryId
     *            栏目ID
     * @param list
     *            栏目集合
     * @param columnWebsiteId
     *            站点ID
     */
    private void queryExpansionColumnListByWebsiteId(int categoryCategoryId, List<ColumnEntity> list,
                                                     int columnWebsiteId) {
        List<ColumnEntity> queryChildList = new ArrayList<ColumnEntity>();
        queryChildList = columnDao.queryColumnByCategoryIdAndWebsiteIdAndModelId(categoryCategoryId, columnWebsiteId,
                null, null);
        for (int i = 0; i < queryChildList.size(); i++) {
            list.add(queryChildList.get(i));
        }

        if (categoryCategoryId != 0) {
            ColumnEntity columnEntity = (ColumnEntity) (columnDao.getEntity(categoryCategoryId));
            queryExpansionColumnListByWebsiteId(columnEntity.getCategoryCategoryId(), list, columnWebsiteId);
        }
    }

    /**
     * 通过栏目ID查询该栏目同级栏目
     *
     * @param columnId
     *            栏目ID
     * @return 同级栏目集合
     */
    public List<ColumnEntity> querySibling(int columnId, Integer size) {
        ColumnEntity columnEntity = (ColumnEntity) columnDao.getEntity(columnId);
        List<ColumnEntity> list = new ArrayList<ColumnEntity>();
        if (columnEntity != null) {

            list = columnDao.queryColumnByCategoryIdAndWebsiteIdAndModelId(columnEntity.getCategoryCategoryId(),
                    columnEntity.getColumnWebsiteId(), null, size);
        }
        return list;
    }

    /**
     * 通过栏目ID查询顶级栏目的同级栏目
     *
     * @param columnId
     *            栏目ID
     * @return 顶级同级栏目集合
     */
    public List<ColumnEntity> queryTopSiblingListByColumnId(int columnId, Integer size) {
        ColumnEntity columnEntity = (ColumnEntity) columnDao.getEntity(columnId);
        List<ColumnEntity> list = null;
        if (columnEntity != null) {
            list = querySibling(columnEntity.getCategoryCategoryId(), size);
        }
        return list;
    }

    /**
     * 根据栏目Id查询栏目的子栏目集
     *
     * @param columnId
     *            栏目ID
     * @return 子栏目集合
     */
    public List<ColumnEntity> queryChildListByColumnId(int columnId, Integer size) {
        ColumnEntity columnEntity = (ColumnEntity) columnDao.getEntity(columnId);
        List<ColumnEntity> list = null;
        if (columnEntity != null) {
            list = columnDao.queryColumnByCategoryIdAndWebsiteIdAndModelId(columnEntity.getCategoryId(),
                    columnEntity.getColumnWebsiteId(), null, size);
        }
        return list;
    }

    /**
     * 根据栏目ID查询其子栏目ID集合
     *
     * @param categoryId
     *            栏目ID
     * @return 子栏目ID集合
     */
    public int[] queryChildIdsByColumnId(int categoryId, int appId) {
        List<Integer> ids = columnDao.queryColumnChildIdList(categoryId, appId);
        int[] ret = new int[ids.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = ids.get(i).intValue();
        return ret;
    }

    /**
     * 根据栏目属性查询栏目站点id为columnWebsiteId的栏目集合
     *
     * @param columnType
     *            栏目属性
     * @param columnWebsiteId
     *            栏目所属站点ID
     * @return 栏目集合
     */
    public List<ColumnEntity> queryColumnListBycolumnType(int columnType, int columnWebsiteId) {

        return columnDao.queryColumnListBycolumnType(columnType, columnWebsiteId);
    }

    /**
     * 用递归通过栏目ID查询栏目的父级栏目,将查询结果装入List集合中
     *
     * @param columnId
     *            栏目ID
     * @param list
     *            父级栏目集合
     */
    private void queryColumnParent(ColumnEntity column, List<ColumnEntity> list) {
        if (column.getCategoryCategoryId() != 0) {
            ColumnEntity columnEntity = (ColumnEntity) columnDao.getEntity(column.getCategoryCategoryId());
            list.add(columnEntity);
            queryColumnParent(columnEntity, list);
        }
    }

    /**
     * 通过栏目ID查询栏目对应节点路径上的父级栏目集合
     *
     * @param columnId
     *            栏目ID
     * @return 栏目及其父级栏目集合
     */
    public List<ColumnEntity> queryParentColumnByColumnId(int columnId) {
        List<ColumnEntity> list = null;
        ColumnEntity columnEntity = (ColumnEntity) columnDao.getEntity(columnId);
        if (columnEntity != null) {
            list = new ArrayList<ColumnEntity>();
            // 递归的查询所有父节点
            queryColumnParent(columnEntity, list);
        }
        return list;
    }

    /**
     * 根据站点Id查询该站点下的栏目的父栏目Id为categoryCategoryId的子栏目集合数目统计
     *
     * @param categoryCategoryId
     *            父栏目ID
     * @param columnWebsiteId
     *            站点ID
     * @return 子栏目统计数目
     */
    public int queryColumnChildListCountByWebsiteId(int categoryCategoryId, int columnWebsiteId) {
        return columnDao.queryColumnChildListCountByWebsiteId(categoryCategoryId, columnWebsiteId);
    }

    /**
     * 通过管理员ID和模块ID查询订单类型集合
     *
     * @param categoryManagerId
     *            管理员ID
     * @param categoryModelId
     *            模块ID
     * @return 返回订单集合
     */
    public List<BasicEntity> queryCategoryByManagerIdAndModelId(int categoryManagerId, int categoryModelId) {
        return columnDao.queryCategoryByManagerIdAndModelId(categoryManagerId, categoryModelId);
    }

    @Override
    public List<ColumnEntity> querSibListByColumnId(int columnId) {
        // TODO Auto-generated method stub
        ColumnEntity columnEntity = (ColumnEntity) columnDao.getEntity(columnId);
        List<ColumnEntity> list = null;
        if (columnEntity != null) {
            if (columnEntity.getCategoryId() != 0) {
                list = columnDao.queryColumnByCategoryIdAndWebsiteIdAndModelId(columnEntity.getCategoryId(),
                        columnEntity.getColumnWebsiteId(), null, null);
            }

        }
        return list;
    }
}
