/**
 * Title: QueryEnvForAppOutputBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 环境列表ForApp输出接口
 * @author xuph
 */
public class QueryEnvForAppOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : 2437206731354555501L
	 */ 
	private static final long serialVersionUID = 2437206731354555501L;
	
	/**
	 * 环境信息列表
	 */
	private List<EnvInfoBean> env_list;
	
	public static final String ENV_LISTCN = "环境信息列表";

	/**
	 * @return env_list 环境信息列表
	 */
	public List<EnvInfoBean> getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list 环境信息列表
	 */
	public void setEnv_list(List<EnvInfoBean> env_list) {
		this.env_list = env_list;
	}

}
