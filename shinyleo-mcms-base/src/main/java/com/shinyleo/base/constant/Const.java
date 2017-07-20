package com.shinyleo.base.constant;

import org.springframework.context.ApplicationContext;

import java.util.ResourceBundle;

/**
 * Created by zhugh on 17/7/20.
 */
public class Const {


    /**
     * 当前请求路径,BaseFilter赋值
     */
    public static String BASE_URL;

    /**
     * 项目名称,BaseFilter赋值
     */
    public static String BASE;

    /**
     * 项目物理路径,BaseFilter赋值
     */
    public static String PROJECT_PATH;

    /**
     * spring资源文件加载上下文对象
     */
    public static ApplicationContext CONTEXT;

    /**
     * action层对应的国际化资源文件
     */
    public final static ResourceBundle RESOURCES = ResourceBundle.getBundle("com.mingsoft.base.resources.resources");



    /**
     * 服务器发布地址,带有http:// 在StrutsFilter类里面设置
     */
    public static String HOST_URL = "";


    /**
     * 默认编码格式
     */
    public final static String UTF8 = "utf-8";

    /**
     * URL路径符
     */
    public final static String SEPARATOR ="/";
}
