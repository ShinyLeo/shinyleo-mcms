package com.shinyleo.basic.entity;

import com.shinyleo.base.entity.BaseEntity;

import java.sql.Timestamp;

/**
 * Created by shinyleo on 17/7/20.
 */
public class CategoryEntity extends BaseEntity {

    /**
     * 分类所属应用编号
     */
    private int categoryAppId;

    /**
     * 父类别的编号
     */
    private int categoryCategoryId;

    /**
     * 类别发布时间
     */
    private Timestamp categoryDateTime;

    /**
     * 栏目描述
     */
    private String categoryDescription;

    /**
     * 类别的编号自增长id
     */
    private int categoryId;

    /**
     * 栏目层级数量，方便控制后台分类的层级数量
     * 不参与表结构
     */
    private int categoryLevel;



    /**
     * 发布用户编号(发布者编号)
     */
    private int categoryManagerId;

    /**
     * 所属模块编号
     */
    private int categoryModelId;

    /**
     * 缩略图
     */
    private String categorySmallImg;

    /**
     * 类别的排序
     */
    private int categorySort;

    /**
     * 类别的标题
     */
    private String categoryTitle;

    private int categoryDel;

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(int appId,int modelId){
        this.categoryAppId = appId;
        this.categoryModelId = modelId;
    }

    public CategoryEntity(int categoryId,String categoryTitle) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryAppId() {
        return categoryAppId;
    }

    public int getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public Timestamp getCategoryDateTime() {
        return categoryDateTime;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public int getCategoryManagerId() {
        return categoryManagerId;
    }

    public int getCategoryModelId() {
        return categoryModelId;
    }

    public String getCategorySmallImg() {
        return categorySmallImg;
    }

    public int getCategorySort() {
        return categorySort;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryAppId(int categoryAppId) {
        this.categoryAppId = categoryAppId;
    }


    public void setCategoryCategoryId(int categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public void setCategoryDateTime(Timestamp categoryDateTime) {
        this.categoryDateTime = categoryDateTime;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public void setCategoryManagerId(int categoryManagerId) {
        this.categoryManagerId = categoryManagerId;
    }

    public void setCategoryModelId(int categoryModelId) {
        this.categoryModelId = categoryModelId;
    }

    public void setCategorySmallImg(String categorySmallImg) {
        this.categorySmallImg = categorySmallImg;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryDel() {
        return categoryDel;
    }

    public void setCategoryDel(int categoryDel) {
        this.categoryDel = categoryDel;
    }
}
