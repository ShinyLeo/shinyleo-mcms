package com.shinyleo.people.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.util.*;
import java.util.*;
import com.shinyleo.people.entity.BasicPeopleEntity;

/**
 * 通用用户与信息一对多表持久层
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicPeopleDao extends IBaseDao {
	/**
	 * 查询
	 * @param basicPeople 通用用户与信息一对多表
	 * @return
	 */
	List query(BasicPeopleEntity basicPeople);
}