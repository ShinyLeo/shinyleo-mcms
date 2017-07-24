package com.shinyleo.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.biz.IManagerBiz;
import com.shinyleo.basic.biz.IModelBiz;
import com.shinyleo.basic.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */

@Controller
@RequestMapping("/${managerPath}/model")
public class ModelAction extends BaseAction  {

    /**
     * 注入模块业务层
     */
    @Autowired
    private IModelBiz modelBiz;

    @Autowired
    private IManagerBiz managerBiz;




    /**
     * 根据管理员ID查询模块集合
     * @param managerId 管理员id
     * @param request 请求对象
     * @param response 响应对象
     */
    @RequestMapping("/{managerId}/queryModelByRoleId")
    public void queryModelByRoleId(@PathVariable int managerId, HttpServletRequest request, HttpServletResponse response) {
        ManagerEntity manager =(ManagerEntity) managerBiz.getEntity(managerId);
        if(manager==null){
            return;
        }
        List<BaseEntity> modelList = new ArrayList<BaseEntity>();
        modelList = modelBiz.queryModelByRoleId(manager.getManagerRoleID());
        this.outJson(response, null,true, JSONObject.toJSONString(modelList));

    }

}
