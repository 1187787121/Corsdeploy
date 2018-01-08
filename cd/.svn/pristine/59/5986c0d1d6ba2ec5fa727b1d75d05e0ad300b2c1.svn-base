/**
 * Title: CLSLSTP.java
 * File Description: 数组类结构
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-19
 */
package com.wk.cd.common.bean;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.exc.CannotCloneException;

/**
 * Class Description: 数组类结构
 * @author lixl
 */
public class CLSLSTP implements Cloneable{
	private String flstname;
	private String flstclsname;
	private List<CLSP> fclsp;
	

	CLSLSTP(String flstclsname, String flstname, String fname, String fval, String ftype){
		this.flstname = flstname;
		this.flstclsname = flstclsname;
		this.fclsp = new ArrayList<CLSP>();
		CLSP clsp = new CLSP(fname, fval, ftype);
		fclsp.add(clsp);
	}
	
	/**
	 * @return flstname List名称
	 */
	public String getFlstname() {
		return this.flstname;
	}

	/**
	 * @param flstname List名称
	 */
	void setFlstname(String flstname) {
		this.flstname = flstname;
	}

	/**
	 * @return flstclsname List<T>T类型
	 */
	public String getFlstclsname() {
		return this.flstclsname;
	}

	/**
	 * @param flstclsname List<T>T类型
	 */
	void setFlstclsname(String flstclsname) {
		this.flstclsname = flstclsname;
	}

	/**
	 * @return fclsp 类属性元素数组
	 */
	public List<CLSP> getFclsp() {
		return this.fclsp;
	}

	/**
	 * @param fclsp 类属性元素数组
	 */
	void setFclsp(List<CLSP> fclsp) {
		this.fclsp = fclsp;
	}

	void addFclsp(CLSP clsp){
		fclsp.add(clsp);
	}
	
	@Override
	public CLSLSTP clone(){
		try{
			return (CLSLSTP)super.clone();
		}catch(CloneNotSupportedException e){
			throw new CannotCloneException().addScene("CLASS",CLSLSTP.class.getName());
		}
	}
}
