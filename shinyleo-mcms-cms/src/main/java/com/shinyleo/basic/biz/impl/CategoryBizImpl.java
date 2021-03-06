package com.shinyleo.basic.biz.impl;

import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.biz.ICategoryBiz;
import com.shinyleo.basic.dao.ICategoryDao;
import com.shinyleo.basic.entity.CategoryEntity;
import com.shinyleo.util.PageUtil;
import com.shinyleo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service("categoryBiz")
public class CategoryBizImpl  extends BaseBizImpl implements ICategoryBiz {

    /**
     * 注入类别持久化层
     */
    private ICategoryDao categoryDao;

    /**
     * 获取类别持久化层
     * @return categoryDao 返回类别持久化层
     */
    public ICategoryDao getCategoryDao() {
        return categoryDao;
    }

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * 获取类别持久化层
     * @return categoryDao 返回类别持久话层
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return categoryDao;
    }

    @Override
    public int saveCategory(CategoryEntity categoryEntity) {
        // TODO Auto-generated method stub
        categoryDao.saveEntity(categoryEntity);
        return saveEntity(categoryEntity);
    }

    @Override
    public int saveCategoryEntity(CategoryEntity categoryEntity) {
        // TODO Auto-generated method stub
        return categoryDao.saveEntity(categoryEntity);
    }

    @Override
    public void deleteCategory(int categoryId) {
        // TODO Auto-generated method stub
        categoryDao.deleteEntity(categoryId);
        deleteEntity(categoryId);
    }

    @Override
    public void deleteCategoryEntity(int categoryId) {
        // TODO Auto-generated method stub
        categoryDao.deleteEntity(categoryId);
    }

    @Override
    public void updateCategory(CategoryEntity categoryEntity) {
        // TODO Auto-generated method stub
        categoryDao.updateEntity(categoryEntity);
        updateEntity(categoryEntity);
    }

    @Override
    public void updateCategoryEntity(CategoryEntity categoryEntity) {
        // TODO Auto-generated method stub
        categoryDao.updateEntity(categoryEntity);
    }

    @Override
    public CategoryEntity getCategory(int categoryId) {
        // TODO Auto-generated method stub
        return (CategoryEntity)categoryDao.getEntity(categoryId);
    }

    @Override
    public List queryByPageList(CategoryEntity category, PageUtil page, String orderBy, boolean order) {
        // TODO Auto-generated method stub
        return categoryDao.queryByPageList(category,page, orderBy, order);
    }

    @Override
    public List<CategoryEntity> queryChilds(CategoryEntity category) {
        // TODO Auto-generated method stub
        return categoryDao.queryChilds(category);
    }

    @Override
    public int count(CategoryEntity category) {
        // TODO Auto-generated method stub
        return categoryDao.count(category);
    }


    @Override
    public List<CategoryEntity> queryByModelId(CategoryEntity category){
        // TODO Auto-generated method stub
        return categoryDao.queryByModelId(category);
    }

    @Override
    public List<Integer> queryCategoryIdByTitle(String categoryTitle,int categoryModelId){
        // TODO Auto-generated method stub
        return categoryDao.queryCategoryIdByTitle(categoryTitle,categoryModelId);
    }

    @Override
    public List<Integer> queryCategoryIdByCategoryTitle(String categorySchoolName,int schoolModelId,int facultyModelId){
        // TODO Auto-generated method stub
        return categoryDao.queryCategoryIdByCategoryTitle(categorySchoolName, schoolModelId, facultyModelId);
    }

    /**
     * 根据分类标题和分类的模块ID查询该分类是否存在</br>
     * 若存在则不持久化直接返回数据库中原来的数据</br>
     * 若不存在则持久化并返回实体信息
     *
     * @param categoryTitle
     *            分类标题
     * @param categoryCategoryId
     *            父ID
     * @param categoryModelId
     *            模块ID
     * @return 返回分类实体
     */
    @Override
    public CategoryEntity saveByCategoryTitle(String categoryTitle,int categoryCategoryId,int categoryModelId){
        // TODO Auto-generated method stub
        CategoryEntity category = new  CategoryEntity();
        int categoryId = 0;
        if(StringUtil.isBlank(categoryTitle)){
            category.setCategoryId(categoryId);
            return category;
        }else{
            //查询数据库中属否存在该分类数据
            List <Integer> list = queryCategoryIdByTitle(categoryTitle,categoryModelId);
            if(list != null && list.size()>0){
                categoryId = list.get(list.size()-1);
            }
        }

        //当数据库中不存在该分类数据时则持久化
        if( categoryId == 0){
            category.setCategoryTitle(categoryTitle);
            category.setCategoryCategoryId(categoryCategoryId);
            category.setCategoryModelId(categoryModelId);
            saveCategoryEntity(category);
        }else{
            category.setCategoryId(categoryId);
        }
        return category;
    }

    @Override
    public List<CategoryEntity> queryBatchCategoryById(List<Integer> listId){
        // TODO Auto-generated method stub
        return categoryDao.queryBatchCategoryById(listId);
    }

    @Override
    public List<CategoryEntity> queryChildrenCategory(int categoryId,int appId,int modelId) {
        // TODO Auto-generated method stub
        return categoryDao.queryChildrenCategoryId(categoryId,appId,modelId);
    }

    @Override
    public synchronized int[] queryChildrenCategoryIds(int categoryId,int appId,int modelId) {
        // TODO Auto-generated method stub
        List<CategoryEntity> list = categoryDao.queryChildrenCategoryId(categoryId,appId,modelId);
        int[] ids = new int[list.size()];
        for (int i=0;i<list.size();i++) {
            CategoryEntity category = list.get(i);
            ids[i]=category.getCategoryId();
        }
        return ids;
    }

    @Override
    public List<CategoryEntity> queryByAppIdOrModelId(Integer appId, Integer modelId) {
        // TODO Auto-generated method stub
        return categoryDao.queryByAppIdOrModelId(appId,modelId);
    }

    /* (non-Javadoc)
     * @see com.shinyleo.basic.biz.ICategoryBiz#queryParent(int, int, java.lang.Integer)
     */
    @Override
    public List<CategoryEntity> queryParent(int appId, int modelId, Integer categoryId) {
        // TODO Auto-generated method stub
        //先查出父ids
        String ids = categoryDao.queryParentIds(categoryId);
        if (!StringUtil.isBlank(ids)) {
            List list =new ArrayList();
            String[] _ids = ids.split(",");
            for (int i=0;i<_ids.length;i++) {
                list.add(Integer.parseInt(_ids[i]));
            }
            return categoryDao.queryBatchCategoryById(list);
        }
        return null;
    }

    @Override
    public List<CategoryEntity> queryByDescription(int appId, int modelId,
                                                   String categoryDescription) {
        // TODO Auto-generated method stub
        if(StringUtil.isBlank(categoryDescription)){
            return null;
        }
        return this.categoryDao.queryByDescription(appId, modelId,categoryDescription);
    }

    @Override
    public int[] queryCategoryIdsByModelIdAndAppId(int appId,int modelId){
        // TODO Auto-generated method stub
        List<CategoryEntity> list = categoryDao.queryByAppIdOrModelId(appId, modelId);
        int ids[] = new int[list.size()];
        for (int i=0;i<list.size();i++) {
            CategoryEntity category = list.get(i);
            ids[i]=Integer.valueOf(category.getCategoryId());
        }
        return ids;
    }

    @Override
    public List<CategoryEntity> queryByCategoryTitle(String[] title) {
        // TODO Auto-generated method stub
        return categoryDao.queryByCategoryTitle(title);
    }
}
