
package com.shinyleo.people.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinyleo.people.biz.IPeopleBiz;
import com.shinyleo.people.constant.ModelCode;
import com.shinyleo.people.constant.e.PeopleEnum;
import com.shinyleo.people.entity.PeopleEntity;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
@Controller
@RequestMapping("/${managerPath}/people")
public class PeopleAction extends BaseAction{
	
	/**
	 * 注入用户控制层
	 */
	@Autowired
	private IPeopleBiz peopleBiz;
	
	/**
	 * 更新用户状态
	 * @param people 用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateState")
	public void updateState(@ModelAttribute PeopleEntity people,HttpServletRequest request,HttpServletResponse response){
		if(people == null){
			this.outJson(response, ModelCode.PEOPLE,false);
			return ;
		}
		
		if(people.getPeopleState() == PeopleEnum.STATE_CHECK.toInt()){
			people.setPeopleState(PeopleEnum.STATE_NOT_CHECK);
		}else{
			people.setPeopleState(PeopleEnum.STATE_CHECK);
		}
		this.peopleBiz.updateEntity(people);
		this.outJson(response, ModelCode.PEOPLE,true,Integer.toString(people.getPeopleState()));
	}
}