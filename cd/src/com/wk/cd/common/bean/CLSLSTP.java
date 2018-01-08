/**
 * Title: CLSLSTP.java
 * File Description: ������ṹ
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
 * Class Description: ������ṹ
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
	 * @return flstname List����
	 */
	public String getFlstname() {
		return this.flstname;
	}

	/**
	 * @param flstname List����
	 */
	void setFlstname(String flstname) {
		this.flstname = flstname;
	}

	/**
	 * @return flstclsname List<T>T����
	 */
	public String getFlstclsname() {
		return this.flstclsname;
	}

	/**
	 * @param flstclsname List<T>T����
	 */
	void setFlstclsname(String flstclsname) {
		this.flstclsname = flstclsname;
	}

	/**
	 * @return fclsp ������Ԫ������
	 */
	public List<CLSP> getFclsp() {
		return this.fclsp;
	}

	/**
	 * @param fclsp ������Ԫ������
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
