/**
 * Title: DppBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017��2��14��
 */
package com.wk.cd.module1.bean;

import java.util.List;

import com.wk.cd.module1.info.ParamInfo;

/**
 * Class Description: cl��Ŀ���漰����Ͷ����������Ӧ��ʵ��
 * @author zhangj
 */
public class DppBean {
	
	/**
	 * Ͷ���� ����
	 */
	String package_name;
	
	/**
	 * Ͷ��������Ӧ��Ͷ���������б�
	 */
	List<ParamInfo> dpp_params;

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public List<ParamInfo> getDpp_params() {
		return dpp_params;
	}

	public void setDpp_params(List<ParamInfo> dpp_params) {
		this.dpp_params = dpp_params;
	}
	
	

}