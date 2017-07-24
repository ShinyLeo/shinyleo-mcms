package com.shinyleo.basic.interceptor;

import com.shinyleo.base.constant.Const;
import com.shinyleo.basic.constant.e.SessionConstEnum;
import com.shinyleo.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shinyleo on 17/7/20.
 */
public class ActionInterceptor  extends BaseInterceptor{

    @Value("${managerPath}")
    private String managerPath;

    @Value("${managerViewPath}")
    private String managerViewPath;

    /**
     * 所有action的拦截,主要拦截base与basepath
     *
     * @param request
     *            HttpServletRequest对象
     * @param response
     *            HttpServletResponse 对象
     * @param handler
     *            处理器
     * @throws Exception
     *             异常处理
     * @return 处理后返回true
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String modelId = request.getParameter(MODEL_ID); // 获取模块编号
        if (StringUtil.isInteger(modelId)) {
            request.getSession().setAttribute(SessionConstEnum.MODEL_ID_SESSION.toString(), modelId);
            request.getSession().setAttribute(SessionConstEnum.MODEL_TITLE_SESSION.toString(),request.getParameter("modelTitle"));
            request.getSession().setAttribute(SessionConstEnum.MODEL_NAME_SESSION.toString(), request.getParameter("modelName"));
        }
        com.shinyleo.basic.constant.Const.VIEW = this.managerViewPath;
        String base = request.getScheme() + "://" + request.getServerName()
                + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort());
        request.setAttribute(BASE_MANAGER_PATH, managerPath);
        request.setAttribute(BASE, Const.BASE);
        request.setAttribute(MANAGER_PATH,Const.BASE+managerPath);
        request.setAttribute(MANAGER_VIEW_PATH,managerViewPath);
        request.setAttribute(BASE_PATH, base + Const.BASE);
        request.setAttribute(BASE_URL, base + request.getContextPath() + request.getServletPath()
                + (request.getQueryString() == null ? "" : "?" + request.getQueryString()));

        request.setAttribute(PARAMS, assemblyRequestUrlParams(request));
        return true;
    }

}
