/**
 * Title: CLSP.java
 * File Description: 类属性结构
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-19
 */
package com.wk.cd.common.bean;

import com.wk.cd.exc.CannotCloneException;

/**
 * Class Description: 类属性结构
 * @author lixl
 */
public class CLSP implements Cloneable{
	private String fstruname;

	private String fname;

	private String fval;

	private String ftype;

	/**
	 * 构造函数
	 * @param fname
	 * @param fval
	 * @param ftype
	 */
	public CLSP(String fname, String fval, String ftype){
		this.fname = fname;
		this.fval = fval;
		this.ftype = ftype;
		this.fstruname = "";
	}

	/**
	 * @return String 结构变量名
	 */
	public String getFstruname(){
		return this.fstruname;
	}

	/**
	 * @param fstruname 结构变量名
	 */
	public void setFstruname(String fstruname){
		this.fstruname = fstruname;
	}

	/**
	 * @return fname 属性名称
	 */
	public String getFname() {
		return this.fname;
	}

	/**
	 * @param fname 属性名称
	 */
	void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return fval 属性值
	 */
	public String getFval() {
		return this.fval;
	}

	/**
	 * @param fval 属性值
	 */
	void setFval(String fval) {
		this.fval = fval;
	}

	/**
	 * @return ftype 类型
	 */
	public String getFtype() {
		return this.ftype;
	}

	/**
	 * @param ftype 类型
	 */
	void setFtype(String ftype) {
		this.ftype = ftype;
	}
	
	@Override
	public CLSP clone(){
		try {
			return (CLSP)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new CannotCloneException().addScene("CLASS",this.getClass().getName());
		}
	}
}
