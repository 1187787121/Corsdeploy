/**
 * Title: PageBuildTaskInputBean.java
 * File Description: 根据环境查询构建任务列表接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: 根据环境查询构建任务列表接口
 * @author wangj
 */
public class PageBuildTaskInputBean extends PageQueryActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 5042436273011495179L;

	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * @return env_name 环境名称
	 */
	public String getEnv_name() {
		return env_name;
	}
	/**
	 * @param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}
}
