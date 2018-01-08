/**
 * Title: SystemServerBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017��3��1��
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.enu.SYS_TYPE;

/**
 * Class Description:
 * @author yangl
 */
public class SystemTypeServerBean {

	/**
	 * ϵͳ����
	 */
	private SYS_TYPE sys_type;

	/**
	 * �������б�
	 */
	private List<ServerBean> server_list;

	/**
	 * @return sys_type ϵͳ����
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type ϵͳ����
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 * @return server_list �������б�
	 */
	public List<ServerBean> getServer_list() {
		return server_list;
	}

	/**
	 * @param server_list �������б�
	 */
	public void setServer_list(List<ServerBean> server_list) {
		this.server_list = server_list;
	}

}
