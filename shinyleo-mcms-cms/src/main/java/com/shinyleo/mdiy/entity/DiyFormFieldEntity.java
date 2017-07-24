package com.shinyleo.mdiy.entity;


import com.shinyleo.base.constant.e.TableCloumnEnum;
import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.mdiy.constant.e.DiyFormFieldEnum;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public class DiyFormFieldEntity extends BaseEntity {
	/**
	 * 字段的默认值
	 */
	private String diyFormFieldDefault;
	
	/**
	 * 字段名称
	 */
	private String diyFormFieldFieldName;
	
	/**
	 * 对应的自定义from的id
	 */
	private int diyFormFieldFormId;
	
	/**
	 * 自增长ID
	 */
	private int diyFormFieldId;
	
	/**
	 * 判断字段为必填还是可选
	 * 参考：DiyFormFieldEnum.IS_NULL
	 */
	private int diyFormFieldIsNull;
	
	/**
	 * 排序
	 */
	private int diyFormFieldSort;
	
	/**
	 * 字段提示文字
	 */
	private String diyFormFieldTipsName;
	
	/**
	 * 长度
	 */
	private int diyFormFieldLength = 1;
	
	/**
	 * 字段类型
	 * 参考：DiyFormFieldEnum
	 */
	private int diyFormFieldType;

	
	
	
	public int getDiyFormFieldLength() {
		return diyFormFieldLength;
	}

	public void setDiyFormFieldLength(int diyFormFieldLength) {
		this.diyFormFieldLength = diyFormFieldLength;
	}

	public String getDiyFormFieldDefault() {
		if (this.diyFormFieldType == DiyFormFieldEnum.DATE.toInt()
				|| this.diyFormFieldType == DiyFormFieldEnum.TEXTAREA.toInt()) {
			return null;
		} else if (this.diyFormFieldType == DiyFormFieldEnum.FLOAT.toInt()
				|| this.diyFormFieldType == DiyFormFieldEnum.INT.toInt()
				)  {
			return "0";	
		}
				

		return diyFormFieldDefault;
	}

	public String getDiyFormFieldFieldName() {
		return diyFormFieldFieldName;
	}

	public int getDiyFormFieldFormId() {
		return diyFormFieldFormId;
	}

	public int getDiyFormFieldId() {
		return diyFormFieldId;
	}

	public int getDiyFormFieldIsNull() {
		return diyFormFieldIsNull;
	}

	public int getDiyFormFieldSort() {
		return diyFormFieldSort;
	}

	public String getDiyFormFieldTipsName() {
		return diyFormFieldTipsName;
	}

	public int getDiyFormFieldType() {
		
		return diyFormFieldType;
	}

	
	public String getDiyFormFieldColumnType() {
		if (this.getDiyFormFieldType() == DiyFormFieldEnum.DATE.toInt()) {
			return TableCloumnEnum.DATETIME.toString();
		} else if (this.getDiyFormFieldType() == DiyFormFieldEnum.FLOAT.toInt()) {
			return TableCloumnEnum.FLOAT.toString()+"(11)";
		} else if (this.getDiyFormFieldType() == DiyFormFieldEnum.INT.toInt()) {
			return TableCloumnEnum.INT.toString()+"(11)";
		} else if (this.getDiyFormFieldType() == DiyFormFieldEnum.TEXTAREA.toInt()) {
			return TableCloumnEnum.TEXT.toString();
		} else {
			return TableCloumnEnum.VARCHAR.toString()+"(100)";
		}

	}

	
	public void setDiyFormFieldDefault(String diyFormFieldDefault) {
		this.diyFormFieldDefault = diyFormFieldDefault;
	}
	public void setDiyFormFieldFieldName(String diyFormFieldFieldName) {
		this.diyFormFieldFieldName = diyFormFieldFieldName;
	}
	
	public void setDiyFormFieldFormId(int diyFormFieldFormId) {
		this.diyFormFieldFormId = diyFormFieldFormId;
	}

	public void setDiyFormFieldId(int diyFormFieldId) {
		this.diyFormFieldId = diyFormFieldId;
	}

	public void setDiyFormFieldIsNull(int diyFormFieldIsNull) {
		this.diyFormFieldIsNull = diyFormFieldIsNull;
	}

	public void setDiyFormFieldSort(int diyFormFieldSort) {
		this.diyFormFieldSort = diyFormFieldSort;
	}

	public void setDiyFormFieldTipsName(String diyFormFieldTipsName) {
		this.diyFormFieldTipsName = diyFormFieldTipsName;
	}

	public void setDiyFormFieldType(DiyFormFieldEnum diyFormFieldType) {
		this.diyFormFieldType = diyFormFieldType.toInt();
	}

	public void setDiyFormFieldType(int diyFormFieldType) {
		this.diyFormFieldType = diyFormFieldType;
	}

	
	
	
	
}
