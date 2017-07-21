
package com.shinyleo.people.action.people;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.shinyleo.people.biz.IPeopleStudentBiz;
import com.shinyleo.people.constant.ModelCode;
import com.shinyleo.people.entity.PeopleEntity;
import com.shinyleo.people.entity.PeopleStudentEntity;
import com.shinyleo.people.action.BaseAction;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
@Controller("webPeopleStudent")
@RequestMapping("/people/student")
public class PeopleStudentAction extends BaseAction{
	
	@Autowired
	private IPeopleStudentBiz peopleStudentBiz;
	
	/**
	 * 查询学生信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getStudentInfor")
	public void getStudentInfor(HttpServletRequest request,HttpServletResponse response){
		//获取用户session
				PeopleEntity people = this.getPeopleBySession(request); 
				if(people == null){
					//当session不存在返回错误信息
					this.outJson(response,ModelCode.PEOPLE_USER,false,this.getResString("people.session.msg.null.error",com.shinyleo.people.constant.Const.RESOURCES));
					return ;
				}
				
				PeopleStudentEntity peopleStudents = (PeopleStudentEntity) this.peopleStudentBiz.getEntity(people.getPeopleId());
				if(peopleStudents == null){
					//没用用户详细信息
					this.outJson(response, ModelCode.PEOPLE_USER,false,this.getResString("people.user.msg.null.error",com.shinyleo.people.constant.Const.RESOURCES));
					return ;
				}
				//查询学生的具体信息
				this.outJson(response, ModelCode.PEOPLE_USER,true,null,JSONObject.toJSONString(this.peopleStudentBiz.getStudentInfor(people.getPeopleId())));
	}
}