package com.shinyleo.basic.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.base.constant.e.BaseEnum;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.constant.e.ModelEnum;
import com.shinyleo.basic.entity.ModelEntity;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IModelBiz  extends IBaseBiz {

    /**
     * 通用分类
     */
    String CATEGORY_MODEL = "99";

    /**
     * 通用文章
     */
    String BASIC_MODEL = "98";
    /**
     * 通用订单
     */
    String ORDER_MODEL = "97";
    /**
     * 通用订单状态
     */
    String ORDER_STATUS_MODEL = "96";

    /**
     * 根据父模块id查找子模块
     * @return 返回子模块集合
     */
    List<BaseEntity> queryChildList(int modelModelId);

    /**
     * 查找顶级模块
     * @return 返回模块集合
     */
    List<BaseEntity> queryParent();

    /**
     * 根据管理员ID查询模块集合
     * @param managerId 管理员ID
     * @return 返回模块集合
     */
    List<BaseEntity> queryModelByManagerId(int managerId, int modelId);

    /**
     * 查找管理员Id不为-1的模块
     * @return 返回模块集合
     */
    List<BaseEntity> queryModelByManager();

    /**
     * 根据角色ID查询模块集合
     * @param roleId 角色ID
     * @return 返回模块集合
     */
    List<BaseEntity> queryModelByRoleId(int roleId);

    /**
     * 根据模块枚举类查询模块集合
     * @param modelEnum 模块枚举类的值
     * @return 返回模块集合
     */
    List<BaseEntity> queryModelByIsMenu(ModelEnum modelEnum);

    /**
     * 根据模块编号查询模块实体
     * @param modelCode 模块编号
     * @return 返回模块实体
     */
    ModelEntity getEntityByModelCode(BaseEnum modelCode);

    /**
     * 根据模块编号查询模块实体
     * @param modelCode 模块编号
     * @return 返回模块实体
     */
    ModelEntity getEntityByModelCode(String modelCode);

    /**
     * 根据模块id获取当前项目中的分类模块id，规则根据modelcode定。**99******,只用是第３位与第４位９９
     * @param modelType 模块类型　
     * @param modelId 模块ID
     * @return 返回模块实体
     */
    ModelEntity getModel(String modelType, int modelId);
}
