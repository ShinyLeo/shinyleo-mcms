package com.shinyleo.basic.action;

import com.alibaba.fastjson.JSONArray;
import com.shinyleo.basic.biz.ISystemSkinBiz;
import com.shinyleo.basic.constant.e.SessionConstEnum;
import com.shinyleo.basic.entity.ManagerSessionEntity;
import com.shinyleo.basic.entity.SystemSkinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
@Controller
@RequestMapping("/${managerPath}/systemSkin/")

public class SystemSkinAciton extends BaseAction{

    /**
     * 主题业务
     */
    @Autowired
    private ISystemSkinBiz systemSkinBiz;

    /**
     * 通过系统主题id更新主题
     * @param request 请求对象
     * @param response 响应对象
     * @param systemSkinId 系统主题id
     */
    @RequestMapping("/{systemSkinId}/updateForManager")
    @ResponseBody
    public void updateForManager(HttpServletRequest request, HttpServletResponse response, @PathVariable int systemSkinId){
        ManagerSessionEntity mse = this.getManagerBySession(request);
        SystemSkinEntity sse = systemSkinBiz.updateManagerSystemSkin(mse.getManagerId(), systemSkinId);
        if (sse!=null) {
            mse.setSystemSkin(sse);
            this.setSession(request, SessionConstEnum.MANAGER_SESSION, mse);
        }
    }

    /**
     * 查询所有主题以json格式返回list集合
     * @param request 请求对象
     * @param response 响应对象
     */
    @RequestMapping("/query")
    @ResponseBody
    public void query(HttpServletRequest request,HttpServletResponse response){
        List list = systemSkinBiz.queryAll();
        this.outJson(response, JSONArray.toJSONString(list));
    }

}
