package com.shinyleo.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.shinyleo.basic.biz.IManagerModelPageBiz;
import com.shinyleo.basic.entity.ManagerModelPageEntity;
import com.shinyleo.basic.entity.ManagerSessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shinyleo on 17/7/20.
 */
@Controller
@RequestMapping("/${managerPath}/managerModelPage")
public class ManagerModelPageAction extends BaseAction {

    /**
     * 注入业务层
     */
    @Autowired
    private IManagerModelPageBiz managerModelPageBiz;

    /**
     * 管理员添加页面模块
     * @param managerModelPage 管理员页面实体
     * @param request 请求对象
     * @param response 响应对象
     */
    @RequestMapping("/save")
    public void save(@ModelAttribute ManagerModelPageEntity managerModelPage, HttpServletRequest request, HttpServletResponse response){
        //根据管理员id查找管理员模块页面实体对象
        ManagerModelPageEntity oldManagerModelPage =(ManagerModelPageEntity) managerModelPageBiz.getByManagerIdAndModelId(managerModelPage.getManagerModelPagemanagerId(),managerModelPage.getManagerModelPageModelId());
        //判断记录是否已经存在，如果存在则进行更新
        if(oldManagerModelPage!=null){
            managerModelPageBiz.updateEntity(managerModelPage);
            this.outJson(response, null, true);
            return;
        }
        managerModelPageBiz.saveEntity(managerModelPage);
        this.outJson(response, null, true);
    }

    /**
     * 根据管理员id查找实体
     * @param managerModelPageModelId 管理员id
     * @param request 请求对象
     * @param response 响应对象
     */
    @RequestMapping("/{managerModelPageModelId}/getEntity")
    public void getEntity(@PathVariable("managerModelPageModelId") int managerModelPageModelId, HttpServletRequest request, HttpServletResponse response){
        //获取
        ManagerSessionEntity managerSession = getManagerBySession(request);
        int managerId = managerSession.getManagerId();
        //根据管理员id查找管理员模块页面实体对象
        ManagerModelPageEntity managerModelPage =(ManagerModelPageEntity) managerModelPageBiz.getByManagerIdAndModelId(managerId,managerModelPageModelId);
        if(managerModelPage==null){
            this.outJson(response,null, false);
            return;
        }
        this.outJson(response,null, true, JSONObject.toJSONString(managerModelPage));
    }

}
