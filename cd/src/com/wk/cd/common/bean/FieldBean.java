/**
 * Title: FieldBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: playg
 * @version: 1.0
 * @date: 2017年8月4日
 */
package com.wk.cd.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Class Description: 
 * @author playg
 */
public class FieldBean implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -6337844944280687477L;
	
	private String fcname;
	
	private String fname;
	
	private String fval;
	
	private String ftype;
	
	private String shtype;
	
	private List<FieldBean> field_list;
	

	/**
	 * 构造函数
	 * @param fcname
	 * @param fname
	 * @param fval
	 * @param ftype
	 * @param shtype
	 * @param field
	 * @param field_list
	 */
	public FieldBean(String fcname, String fname, String fval, String ftype, String shtype, List<FieldBean> field_list) {
		super();
		this.fcname = fcname;
		this.fname = fname;
		this.fval = fval;
		this.ftype = ftype;
		this.shtype = shtype;
		this.field_list = field_list;
	}

	/**
	 * 构造函数
	 */
	public FieldBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return fcname
	 */
	public String getFcname() {
		return fcname;
	}

	/**
	 * @param fcname
	 */
	public void setFcname(String fcname) {
		this.fcname = fcname;
	}

	/**
	 * @return fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return fval
	 */
	public String getFval() {
		return fval;
	}

	/**
	 * @param fval
	 */
	public void setFval(String fval) {
		this.fval = fval;
	}

	/**
	 * @return ftype
	 */
	public String getFtype() {
		return ftype;
	}

	/**
	 * @param ftype
	 */
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	/**
	 * @return shtype
	 */
	public String getShtype() {
		return shtype;
	}

	/**
	 * @param shtype
	 */
	public void setShtype(String shtype) {
		this.shtype = shtype;
	}

	
	
	/**
	 * @return field_list
	 */
	public List<FieldBean> getField_list() {
		return field_list;
	}

	/**
	 * @param field_list
	 */
	public void setField_list(List<FieldBean> field_list) {
		this.field_list = field_list;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "FieldBean [fcname=" + fcname + ", fname=" + fname + ", fval=" + fval + ", ftype=" + ftype + ", shtype=" + shtype + "]";
	}
	
	

}
