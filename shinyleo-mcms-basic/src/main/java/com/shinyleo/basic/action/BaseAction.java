package com.shinyleo.basic.action;

import com.shinyleo.base.constant.e.BaseEnum;
import com.shinyleo.basic.biz.IAppBiz;
import com.shinyleo.basic.biz.IModelBiz;
import com.shinyleo.basic.constant.Const;
import com.shinyleo.basic.constant.e.CookieConstEnum;
import com.shinyleo.basic.constant.e.SessionConstEnum;
import com.shinyleo.basic.entity.AppEntity;
import com.shinyleo.basic.entity.ManagerSessionEntity;
import com.shinyleo.basic.entity.ModelEntity;
import com.shinyleo.basic.interceptor.BaseInterceptor;
import com.shinyleo.basic.parser.IGeneralParser;
import com.shinyleo.parser.IParserRegexConstant;
import com.shinyleo.util.AESUtil;
import com.shinyleo.util.StringUtil;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

/**
 * Created by shinyleo on 17/7/20.
 */
public abstract class BaseAction  extends com.shinyleo.base.action.BaseAction {

    /**
     * 根据当前的模块获取到当前模块的分类模块信息
     *
     * @param request
     *            请求对象
     * @return 错误返回null，正确返回模块实体对象
     */
    protected ModelEntity getCategoryModelCode(HttpServletRequest request) {
        Object obj = this.getSession(request, SessionConstEnum.MODEL_ID_SESSION);
        if (StringUtil.isInteger(obj)) {
            com.shinyleo.basic.biz.IModelBiz modelBiz = (com.shinyleo.basic.biz.IModelBiz) getBean(
                    request.getServletContext(), "modelBiz");
            return modelBiz.getModel(IModelBiz.CATEGORY_MODEL, Integer.parseInt(obj.toString()));
        } else {
            return null;
        }
    }

    /**
     * 根据当前的模块获取到当前模块的basic文章模块信息
     *
     * @param request
     *            请求对象
     * @return 错误返回null，正确返回模块实体对象
     */
    protected ModelEntity getBasicModelCode(HttpServletRequest request) {
        Object obj = this.getSession(request, SessionConstEnum.MODEL_ID_SESSION);
        if (StringUtil.isInteger(obj)) {
            com.shinyleo.basic.biz.IModelBiz modelBiz = (com.shinyleo.basic.biz.IModelBiz) getBean(
                    request.getServletContext(), "modelBiz");
            return modelBiz.getModel(IModelBiz.BASIC_MODEL, Integer.parseInt(obj.toString()));
        } else {
            return null;
        }
    }

    /**
     * 获取管理员id，规则：没有父ID就获取自身的ID
     *
     * @param request
     *            请求对象
     * @return 管理员编号
     */
    protected int getManagerId(HttpServletRequest request) {
        ManagerSessionEntity managerSession = (ManagerSessionEntity) this.getManagerBySession(request);
        int managerParent = managerSession.getManagerParentID();

        if (managerParent == 0) {
            return managerSession.getManagerId();
        } else {
            return managerParent;
        }
    }

    /**
     * 判断当前管理员是否是系统平台管理员
     *
     * @param request
     *            请求对象
     * @return true:是系统平台管理员，false:不是系统平台管理员
     */
    protected boolean isSystemManager(HttpServletRequest request) {
        ManagerSessionEntity manager = (ManagerSessionEntity) getManagerBySession(request);
        if (manager.getManagerRoleID() == Const.DEFAULT_SYSTEM_MANGER_ROLE_ID) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 读取管理员session
     *
     * @param request
     *            请求对象
     * @return 获取不到就返回null
     */
    protected ManagerSessionEntity getManagerBySession(HttpServletRequest request) {
        // 传入用管理员请求，读取管理员的session || super,调用父类的protected属性的getSession方法
        ManagerSessionEntity managerSession = (ManagerSessionEntity) this.getSession(request,
                SessionConstEnum.MANAGER_SESSION);
        if (managerSession != null) {
            // 返回管理员的所有信息
            return managerSession;
        }
        return null;
    }

    @Override
    protected String getResString(String key) {
        // TODO Auto-generated method stub
        String str = "";
        try {
            str = super.getResString(key);
        } catch (MissingResourceException e) {
            str = com.shinyleo.basic.constant.Const.RESOURCES.getString(key);
        }

        return str;
    }

    /**
     * 验证验证码
     *
     * @param request
     *            HttpServletRequest对象
     * @return 如果相同，返回true，否则返回false
     */
    protected boolean checkRandCode(HttpServletRequest request) {
        return checkRandCode(request, SessionConstEnum.CODE_SESSION.toString());
    }

    /**
     * AES解密字符串,key值为当前应用编号
     *
     * @param request
     *            HttpServletRequest对象
     * @param str
     *            需要解密的字符串
     * @return 返回解密后的字符串
     */
    protected String decryptByAES(HttpServletRequest request, String str) {
        return AESUtil.decrypt(str, StringUtil.Md5(this.getApp(request).getAppId() + "").substring(16));
    }

    /**
     * AES加密字符串,key值为当前应用编号
     *
     * @param request
     *            HttpServletRequest对象
     * @param str
     *            需要加密的字符串
     * @return 返回加密后的字符串
     */
    protected String encryptByAES(HttpServletRequest request, String str) {
        return AESUtil.encrypt(str, StringUtil.Md5(this.getApp(request).getAppId() + "").substring(16));
    }

    /**
     * 根据cookie获取历史页码
     *
     * @param request
     *            HttpServletRequest对象
     * @return 返回历史页码，没找到返回０
     */
    protected int getHistoryPageNoByCookie(HttpServletRequest request) {
        if (Integer.valueOf(this.getCookie(request, CookieConstEnum.PAGENO_COOKIE)) >= 1) {
            return Integer.valueOf(this.getCookie(request, CookieConstEnum.PAGENO_COOKIE));
        }
        return 0;
    }


    /**
     * 获取当前模块对应的appid , appid主要根据用户的请求地址获得
     * 推荐使用
     * @param request
     *            HttpServletRequest对象
     * @return 返回appId，找不到对应app,返回0
     */
    @Deprecated
    protected int getAppId(HttpServletRequest request) {
        return this.getApp(request).getAppId();
    }

    /**
     * 获取当前模块对应的appid , appid主要根据用户的请求地址获得
     * 推荐使用
     * @param request
     *            HttpServletRequest对象
     * @return 返回appId，找不到对应app,返回0
     */
    @Deprecated
    protected AppEntity getApp(HttpServletRequest request) {
        AppEntity app = new AppEntity();
        // 获取用户所请求的域名地址
        IAppBiz appBiz = (IAppBiz) getBean(request.getServletContext(), "appBiz");
        AppEntity website = appBiz.getByUrl(this.getDomain(request));
        if (website == null) {
            return null;
        }
        BeanUtils.copyProperties(website, app);
        return app;
    }

    /**
     * 获取验证码
     *
     * @param request
     *            HttpServletRequest对象
     * @return 返回验证码，获取不到返回null
     */
    protected String getRandCode(HttpServletRequest request) {
        return this.getSession(request, SessionConstEnum.CODE_SESSION) + "";
    }

    /**
     * 根据加密过的模块编码获得模块编号
     *
     * @param request
     *            HttpServletRequest对象
     * @param code
     *            编码
     * @return 返回模块编号，如果没有返回0
     */
    protected int getModelCodeIdForAES(HttpServletRequest request, String code) {
        com.shinyleo.basic.biz.IModelBiz modelBiz = (com.shinyleo.basic.biz.IModelBiz) getBean(
                request.getServletContext(), "modelBiz");
        ModelEntity model = modelBiz.getEntityByModelCode(this.decryptByAES(request, code));

        if (model != null) {
            return model.getModelId();
        }
        return 0;
    }

    /**
     * 获取当前模块编号
     *
     * @param request
     *            HttpServletRequest对象
     * @return 返回当前模块编号，没找到返回0
     */
    protected int getModelCodeId(HttpServletRequest request) {
        Object obj = this.getSession(request, SessionConstEnum.MODEL_ID_SESSION);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /**
     * 根据当前模块编码父模块编号
     *
     * @param request
     *            HttpServletRequest对象
     * @return 返回模块编号，如果没有返回0
     */
    protected int getRootModelCodeId(HttpServletRequest request) {
        Object obj = this.getSession(request, SessionConstEnum.MODEL_ID_SESSION);
        if (StringUtil.isInteger(obj)) {
            com.shinyleo.basic.biz.IModelBiz modelBiz = (com.shinyleo.basic.biz.IModelBiz) getBean(
                    request.getServletContext(), "modelBiz");
            ModelEntity model = (ModelEntity) modelBiz.getEntity(Integer.parseInt(obj.toString()));
            return model.getModelModelId();
        } else {
            return 0;
        }
    }

    /**
     * 根据模块编码获得模块编号
     *
     * @param request
     *            HttpServletRequest对象
     * @param code
     *            编码
     * @return 返回模块编号，如果没有返回0
     */
    protected int getModelCodeId(HttpServletRequest request, BaseEnum code) {
        com.shinyleo.basic.biz.IModelBiz modelBiz = (com.shinyleo.basic.biz.IModelBiz) getBean(
                request.getServletContext(), "modelBiz");
        ModelEntity model = modelBiz.getEntityByModelCode(code);
        if (model != null) {
            return model.getModelId();
        }
        return 0;
    }

    /**
     * 根据模块编码获得模块编号
     *
     * @param request
     *            HttpServletRequest对象
     * @param code
     *            编码
     * @return 返回模块编号，如果没有返回0
     * @deprecated 推荐使用加密过的模块编码获取模块编号,如getModelCodeIdForAES
     */
    protected int getModelCodeId(HttpServletRequest request, String code) {
        com.shinyleo.basic.biz.IModelBiz modelBiz = (com.shinyleo.basic.biz.IModelBiz) getBean(
                request.getServletContext(), "modelBiz");
        ModelEntity model = modelBiz.getEntityByModelCode(code);
        if (model != null) {
            return model.getModelId();
        }
        return 0;
    }

    /**
     * 读取管理员session
     *
     * @param request
     *            HttpServletRequest对象
     * @return 返回管理员session，获取不到就返回null
     */
    protected String getCodeBySession(HttpServletRequest request) {
        // 传入用管理员请求，读取管理员的session || super,调用父类的protected属性的getSession方法
        Object obj = this.getSession(request, SessionConstEnum.CODE_SESSION);
        if (obj != null) {
            // 返回管理员的所有信息
            return (String) obj;
        }
        return null;
    }

    /**
     * 返回重定向
     *
     * @param request
     *            HttpServletRequest对象
     * @param flag
     *            true:提供给springMVC返回，false:只是获取地址
     * @return 返回重定向后的地址
     */
    protected String redirectBack(HttpServletRequest request, boolean flag) {
        if (flag) {
            return "redirect:" + this.getCookie(request, CookieConstEnum.BACK_COOKIE);
        } else {
            return this.getCookie(request, CookieConstEnum.BACK_COOKIE);
        }

    }


    /**
     * 解析ms标签内容
     *
     * @param html
     *            模板文件html内容
     * @param req
     *            HttpServletRequest对象
     * @return 返回解析好的内容
     */
    protected String parserMsTag(String html, IGeneralParser parser, HttpServletRequest req) {
        if (StringUtil.isBlank(html)) {
            return "";
        }
        Map map = new HashMap();
        if (this.isMobileDevice(req)) {
            map.put(IGeneralParser.MOBILE, IParserRegexConstant.MOBILE);
        }
        return parser.parse(html, this.getApp(req), map);
    }

    /**
     * 获取模板路径
     *
     * @param req
     *            HttpServletRequest对象
     * @return 返回模板路径
     */
    protected String getTemplatePath(HttpServletRequest req) {
        AppEntity app = this.getApp(req);
        if (app==null) {
            return "";
        }
        String tmpName = app.getAppStyle();// 获取模版名称
        String tmpPath = getRealPath(req, IParserRegexConstant.REGEX_SAVE_TEMPLATE); // 获取系统模版存放物理路径
        String webSiteTmpPath = tmpPath + File.separator + app.getAppId() + File.separator + tmpName;// 根据站点id组装站点信息路径
        // 格式：templets／站点ID/模版风格
        if (this.isMobileDevice(req)) {
            webSiteTmpPath += File.separator + app.getAppMobileStyle(); // 应用移动模板存放路径
        }
        return webSiteTmpPath;
    }

    /**
     * 验证验证码
     *
     * @param param
     *            表单验证码参数名称
     * @param request
     *            HttpServletRequest对象
     * @return 如果相同，返回true，否则返回false
     */
    protected boolean checkRandCode(HttpServletRequest request, String param) {
        String sessionCode = this.getRandCode(request);
        String requestCode = request.getParameter(param);
        LOG.debug("session_code:"+sessionCode+" requestCode:"+requestCode);
        if (sessionCode.equalsIgnoreCase(requestCode)) {
            return true;
        }
        return false;
    }

    /**
     * 视图层路径，会根据配置文件中的后台管理页面进行拼接
     * @param path 页面路径
     * @return 最终视图层路径
     */
    protected String view(String path) {
        return Const.VIEW+path;
    }

    /**
     * 移除url参数
     * @param request
     * @param fitlers 需要移除的字段名称
     */
    @Deprecated
    protected void removeUrlParams(HttpServletRequest request, String[] fitlers) {
        request.setAttribute(BaseInterceptor.PARAMS, this.assemblyRequestUrlParams(request, fitlers));
    }


}
