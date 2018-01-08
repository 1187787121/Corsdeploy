/**
 * Title: PageInteTaskInputBean.java
 * File Description: 分页查询集成任务列表输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: 分页查询集成任务列表输入接口
 * @author Xul
 */
public class PageInteTaskInputBean extends PageQueryActionRootInputBean{

	private static final long serialVersionUID = -7505495082870467919L;
	
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
