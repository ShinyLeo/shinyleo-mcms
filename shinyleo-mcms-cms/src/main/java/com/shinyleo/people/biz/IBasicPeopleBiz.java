

package com.shinyleo.people.biz;

import com.shinyleo.base.biz.IBaseBiz;
import com.shinyleo.util.*;
import java.util.*;
import com.shinyleo.people.entity.BasicPeopleEntity;

/**
 * 通用用户与信息一对多表业务
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：<br/>
 * 历史修订：<br/>
 */
public interface IBasicPeopleBiz extends IBaseBiz {

	/**
	 * 查询
	 * @param basicPeople 通用用户与信息一对多表
	 * @return
	 */
	List query(BasicPeopleEntity basicPeople);
	
	void saveBasicPeople(BasicPeopleEntity basicPeople);
	
	void deleteBasicPeople(BasicPeopleEntity basicPeople);
	
}