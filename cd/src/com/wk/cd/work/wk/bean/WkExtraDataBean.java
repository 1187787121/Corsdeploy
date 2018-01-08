/**
 * Title: WkExtraDataBean.java
 * File Description: 任务附加数据
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年6月21日
 */
package com.wk.cd.work.wk.bean;

/**
 * Class Description: 任务附加数据
 * @author Xul
 */
public class WkExtraDataBean {
	
	//中文名
	private String name;
	
	//值
	private String value;
	
	//类型
	private String type;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
