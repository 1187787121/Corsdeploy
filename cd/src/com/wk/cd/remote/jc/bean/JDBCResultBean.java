/**
 * Title: JDBCResultBean.java
 * File Description: SQLִ�м�¼����
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/25/2014
 */

package com.wk.cd.remote.jc.bean;
import java.util.List;

/**
 * Class Description: SQLִ�м�¼����
 * @author lixl
 */
public class JDBCResultBean {
	private String soc_name;

	private List<Integer> rs_no;

	/**
	 * @return String ����Դ����
	 */
	public String getSoc_name(){
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name){
		this.soc_name = soc_name;
	}

	/**
	 * @return int ��¼����
	 */
	public List<Integer> getRs_no(){
		return this.rs_no;
	}

	/**
	 * @param rs_no ��¼����
	 */
	public void setRs_no(List<Integer> rs_no){
		this.rs_no = rs_no;
	}
}

