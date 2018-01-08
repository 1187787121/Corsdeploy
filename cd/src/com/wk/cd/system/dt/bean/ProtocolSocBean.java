/**
 * Title: ProtocolSocBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月2日
 */
package com.wk.cd.system.dt.bean;

import java.util.List;

import com.wk.cd.enu.PROTOCOL_TYPE;

/**
 * Class Description:
 * @author yangl
 */
public class ProtocolSocBean {

	/**
	 * 协议类型
	 */
	private PROTOCOL_TYPE protocol_type;

	/**
	 * 数据源名列表
	 */
	private List<String> soc_name_list;

	/**
	 * @return 协议类型
	 */
	public PROTOCOL_TYPE getProtocol_type() {
		return protocol_type;
	}

	/**
	 * @param 协议类型
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * @return 数据源名列表
	 */
	public List<String> getSoc_name_list() {
		return soc_name_list;
	}

	/**
	 * @param 数据源名列表
	 */
	public void setSoc_name_list(List<String> soc_name_list) {
		this.soc_name_list = soc_name_list;
	}

}
