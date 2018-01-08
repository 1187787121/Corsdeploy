/**
 * Title: SelectTitleHeadBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016年8月18日
 */
package com.wk.cd.remote.agent.bean;

import com.wk.cd.enu.COL_TYPE;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class SelectTitleHeadBean {
	
	public SelectTitleHeadBean() {
	}
	
	public SelectTitleHeadBean(String key, String value, boolean display,
			boolean primary_key, boolean read_only, COL_TYPE type) {
		super();
		this.key = key;
		this.value = value;
		this.display = display;
		this.primary_key = primary_key;
		this.read_only = read_only;
		this.type = type;
	}

	/**
	 * 列名称
	 */
	private String key;
	
	public static final String KEYCN = "列名称";
	
	/**
	 * 列描述
	 */
	private String value;
	
	public static final String VALUECN = "列描述";
	
	/**
	 * 是否显示标志
	 */
	private boolean display;
	
	public static final String DISPLAYCN = "是否显示标志";
	
	/**
	 * 主键标志
	 */
	private boolean primary_key;
	
	public static final String PRIMARY_KEYCN = "主键标志";
	
	/**
	 * 只读标志
	 */
	private boolean read_only;
	
	public static final String READ_ONLYCN = "只读标志";
	
	/**
	 * 类型
	 */
	private COL_TYPE type;
	
	public static final String TYPECN = "类型";
	
	/**
	 * 字段长度
	 */
	private int col_length ;
	
	public static final String COL_LENGTHCN = "字段长度";

	/**
	 * @return col_name 列名称
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * @param col_name 列名称
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return col_desc 列描述
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param col_desc 列描述
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return display_flag 是否显示标志
	 */
	public boolean isDisplay() {
		return this.display;
	}

	/** 
	 * @param display_flag 是否显示标志
	 */
	public void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * @return primary_flag 主键标志
	 */
	public boolean isPrimary_key() {
		return this.primary_key;
	}

	/**
	 * @param primary_flag 主键标志
	 */
	public void setPrimary_key(boolean primary_key) {
		this.primary_key = primary_key;
	}

	/**
	 * @return read_only 只读标志
	 */
	public boolean isRead_only() {
		return this.read_only;
	}

	/**
	 * @param read_only 只读标志
	 */
	public void setRead_only(boolean read_only) {
		this.read_only = read_only;
	}

	/**
	 * @return type 类型
	 */
	public COL_TYPE getType() {
		return this.type;
	}

	/** 
	 * @param type 类型
	 */
	public void setType(COL_TYPE type) {
		this.type = type;
	}

	/**
	 * @return col_length 字段长度
	 */
	public int getCol_length() {
		return this.col_length;
	}

	/**
	 * @param col_length 字段长度
	 */
	public void setCol_length(int col_length) {
		this.col_length = col_length;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "SelectTitleHeadBean [key=" + key + ", value=" + value + ", display=" + display + ", primary_key=" + primary_key + ", read_only=" + read_only + ", type=" + type
				+ ", col_length=" + col_length + "]";
	}
	
	
	

}
