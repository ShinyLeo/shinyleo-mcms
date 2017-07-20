package com.shinyleo.basic.constant;

import java.util.ResourceBundle;

/**
 * Created by shinyleo on 17/7/20.
 */
public final class Const {

    /**
     * action层对应的国际化资源文件
     */
    public final static ResourceBundle RESOURCES = ResourceBundle.getBundle("com.mingsoft.basic.resources.resources");

    /**
     * 默认系统管理员所对应的角色ID为1
     */
    public final static int DEFAULT_SYSTEM_MANGER_ROLE_ID = 1;

    /**
     * 默认站点管理员所对应的角色ID为2
     */
    public final static int DEFAULT_WEBSITE_MANGER_ROLE_ID = 2;

    /**
     * 默认CMS所对应的模块ID为1
     */
    public final static int DEFAULT_CMS_MODEL_ID = 1;

    /**
     * 顶级栏目的父栏目ID为0
     */
    public final static int COLUMN_TOP_CATEGORY_ID = 0;

    /**
     * 后台文件存放文件夹，
     */
    @Deprecated
    public  static String VIEW = "/manager";
}
