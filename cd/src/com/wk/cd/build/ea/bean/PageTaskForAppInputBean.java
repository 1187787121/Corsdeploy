/**
 * Title: PageTaskForAppInputBean.java
 * File Description: 分页查询环境任务列表输入接口（App端）
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: 分页查询环境任务列表输入接口（App端）
 * @author Xul
 */
public class PageTaskForAppInputBean extends PageQueryActionRootInputBean{

	private static final long serialVersionUID = 7866719643214664439L;
	
	/**
	 *环境名称
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
