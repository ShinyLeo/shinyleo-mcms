package com.shinyleo.mdiy.biz.impl;


import com.shinyleo.base.biz.impl.BaseBizImpl;
import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.mdiy.biz.IDiyFormBiz;
import com.shinyleo.mdiy.dao.IDiyFormDao;
import com.shinyleo.mdiy.dao.IDiyFormFieldDao;
import com.shinyleo.mdiy.entity.DiyFormEntity;
import com.shinyleo.mdiy.entity.DiyFormFieldEntity;
import com.shinyleo.util.PageUtil;
import com.shinyleo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by shinyleo on 17/7/20.
 */
@Service
public class DiyFormBizImpl extends BaseBizImpl implements IDiyFormBiz {

	/**
	 * 自定义表单的默认字段
	 */
	private static final String FORM_ID = "fromID";
	
	private static final String DATE = "date";
	
	/**
	 * 自定义表单生成的表自增长编号
	 */
	private static final String  ID = "id";
	
	/**
	 * 注入自定义表单持久化层
	 */
	@Autowired
	private IDiyFormDao diyFormDao;
	
	/**
	 * 自定义表单字段持久化层的注入
	 */
	@Autowired
	private IDiyFormFieldDao diyFormFieldDao;

	/**
	 * 获取类别持久化层
	 * @return diyFormDao 返回类别持久话层
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return diyFormDao;
	}

	@Override
	public void saveDiyFormData(int formId, Map params) {
		// TODO Auto-generated method stub
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(formId);
		if (dfe == null) {
			return;
		}
		String tableName = dfe.getDiyFormTableName();
		List<DiyFormFieldEntity> filedList = diyFormFieldDao.queryByDiyFormId(formId);
		if (filedList == null) {
			return;
		}
		Map values = builderSqlMap(filedList,params);
		values.put(FORM_ID, formId);
		values.put(DATE, new Date());
		diyFormFieldDao.insertBySQL(tableName,values);
	}

	@Override
	public Map queryDiyFormData(int diyFormId,int appId,PageUtil page) {
		// TODO Auto-generated method stub
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(diyFormId);
		if (dfe!=null) {
			List<DiyFormFieldEntity> fieldList = diyFormFieldDao.queryByDiyFormId(diyFormId);
			List<Map<String,String>> fields = new ArrayList<Map<String,String>>();
			for (int i=0;i<fieldList.size();i++) {
				Map<String,String> field = new HashMap<String,String>();
				field.put(fieldList.get(i).getDiyFormFieldFieldName(), fieldList.get(i).getDiyFormFieldTipsName());
				fields.add(field);
			}
			Map wheres = new HashMap();
			wheres.put(FORM_ID, diyFormId);
			List list = diyFormDao.queryBySQL(dfe.getDiyFormTableName(), null, wheres, page.getPageSize()*page.getPageNo(), page.getPageSize(),ID);
			Map r = new HashMap();
			r.put("fields", fieldList);
			r.put("list", list);
			return r;
		}
		
		return null;
	}


	@Override
	public void deleteQueryDiyFormData(int id,int diyFormId) {
		// TODO Auto-generated method stub
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(diyFormId);
		Map wheres = new HashMap();
		wheres.put(FORM_ID, diyFormId);
		wheres.put(ID, id);
		diyFormDao.deleteBySQL(dfe.getDiyFormTableName(), wheres);
	}

	@Override
	public int countDiyFormData(int diyFormId, int appId) {
		// TODO Auto-generated method stub
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(diyFormId);
		Map wheres = new HashMap();
		wheres.put(FORM_ID, diyFormId);
		return diyFormDao.countBySQL(dfe.getDiyFormTableName(),wheres );
	}

	@Override
	public List query(int appId) {
		// TODO Auto-generated method stub
		return diyFormDao.query(appId);
	}
	

	/**
	 * 遍历出所有字段的信息
	 * 
	 * @param listField 字段列表
	 * @param params 请求参数
	 * @return 返回所有字段的信息
	 */
	private Map builderSqlMap(List listField, Map params) {
		Map mapParams = new HashMap();
		// 遍历字段名
		for (int i = 0; i < listField.size(); i++) {
			DiyFormFieldEntity field = (DiyFormFieldEntity) listField.get(i);
			String fieldName = field.getDiyFormFieldFieldName();
//			int fieldType = field.getDiyFormFieldType();
//			if (fieldType == DiyFormFieldEnum. ) {
//				String langtyp[] = (String[])params.get(fieldName);
//				if (langtyp != null) {
//					StringBuffer sb = new StringBuffer();
//					for (int j = 0; j < langtyp.length; j++) {
//						sb.append(langtyp[j] + ",");
//					}
//					mapParams.put(fieldName, sb.toString());
//				} else {
//					mapParams.put(fieldName, langtyp);
//				}
//			} else {
				if (StringUtil.isBlank(params.get(fieldName))) {
					mapParams.put(fieldName, null);
				} else {
					mapParams.put(fieldName, params.get(fieldName));
				}
			//			}
		}
		return mapParams;
	}

	@Override
	public DiyFormEntity getByTableName(String diyFormTableName) {
		// TODO Auto-generated method stub
		return diyFormDao.getByTableName(diyFormTableName);
	}
	
	@Override
	public void createDiyFormTable(String table, Map<Object, List> fileds) {
		// TODO Auto-generated method stub
		diyFormDao.createDiyFormTable(table, fileds);
	}

	@Override
	public void deleteAll(String[] ids) {
		// TODO Auto-generated method stub
		diyFormDao.deleteAll(ids);
	}
	
}
