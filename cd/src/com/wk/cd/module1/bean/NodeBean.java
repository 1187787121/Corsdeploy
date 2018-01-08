/**
 * Title: ConfigNodeBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017Äê4ÔÂ1ÈÕ
 */
package com.wk.cd.module1.bean;

/**
 * Class Description: 
 * @author yangl
 */
public class NodeBean {

	private String name;
	
	private String ip;

	/**
	 * @return node_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param node_name
	 */
	public void setName(String node_name) {
		this.name = node_name;
	}

	/**
	 * @return node_ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param node_ip
	 */
	public void setIp(String node_ip) {
		this.ip = node_ip;
	}
	
}
