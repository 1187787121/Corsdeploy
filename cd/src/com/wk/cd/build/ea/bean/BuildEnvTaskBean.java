/**
 * Title: PageEnvTaskBean.java
 * File Description: 分页查询构建列表接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年12月17日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.build.ea.info.EnvTaskInfo;

/**
 * Class Description: 分页查询构建列表接口
 * @author Xul
 */
public class BuildEnvTaskBean extends EnvTaskInfo{
	
	private static final long serialVersionUID = -2985384406880449043L;

	/**
	 * 项目简称
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "项目简称";
	
	/**
	 * @return project_short_name 项目简称
	 */
	public String getProject_short_name() {
		return project_short_name;
	}

	/**
	 * @param project_short_name 项目简称
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}
}
