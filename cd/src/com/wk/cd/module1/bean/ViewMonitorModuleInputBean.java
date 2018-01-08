/**
 * Title: MonitorCompTestInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年10月27日
 */
package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class ViewMonitorModuleInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 3212863135208064452L
	 */ 
	private static final long serialVersionUID = 3212863135208064452L;
	
	/**
	 * 监控的组件或组件组的id
	 */
	private String id ;
	
	public static final String IDCN="监控的组件或组件组的id";
	

	/** 
	 * @return id  监控的组件或组件组的id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id 监控的组件或组件组的id
	 */
	public void setId(String id) {
		this.id = id;
	}


	

}
