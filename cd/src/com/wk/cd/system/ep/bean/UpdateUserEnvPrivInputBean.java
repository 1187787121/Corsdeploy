/**
 * Title: UpdateUserEnvPrivInputBean.java
 * File Description: 修改用户应用环境权限输入接口
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017年1月4日
 */
package com.wk.cd.system.ep.bean;

import java.util.List;

import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改用户应用环境权限输入接口
 * @author HT
 */
public class UpdateUserEnvPrivInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8653096989650054902L;

	/**
	 * 用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";
	
	/**
	 * 应用环境权限列表
	 */
	private List<UsUserEnvPrivInfo> env_list;

	public static final String ENV_LISTCN = "应用环境权限列表";

	/**
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return env_list
	 */
	public List<UsUserEnvPrivInfo> getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list
	 */
	public void setEnv_list(List<UsUserEnvPrivInfo> env_list) {
		this.env_list = env_list;
	}
}
