/**
 * Title: ServerEnvSysBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月8日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.ENV_TYPE;

/**
 * Class Description: 
 * @author yangl
 */
public class ServerEnvSysBean {

	/**
	 * 环境名称
	 */
	private String env_name;
	
	/**
	 * 环境类型
	 */
	private ENV_TYPE env_type;
	
	/**
	 * 数据范围
	 */
	private DT_RANGE dt_range;
		
	/**
	 * 构成要素列表
	 */
	private ELE_TYPE[] ele_type_list;
	
	/**
	 * 构成要素
	 */
	private String ele_type;
	
	/**
	 *应用系统名称
	 */
	private String sys_name;

	/**
	 * @return env_name
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return env_type
	 */
	public ENV_TYPE getEnv_type() {
		return env_type;
	}

	/**
	 * @param env_type
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 * @return dt_range
	 */
	public DT_RANGE getDt_range() {
		return dt_range;
	}

	/**
	 * @param dt_range
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

	/**
	 * @return ele_type_list
	 */
	public ELE_TYPE[] getEle_type_list() {
		return ele_type_list;
	}

	/**
	 * @param ele_type_list
	 */
	public void setEle_type_list(ELE_TYPE[] ele_type_list) {
		this.ele_type_list = ele_type_list;
	}

	/**
	 * @return ele_type
	 */
	public String getEle_type() {
		return ele_type;
	}

	/**
	 * @param ele_type
	 */
	public void setEle_type(String ele_type) {
		this.ele_type = ele_type;
	}

	/**
	 * @return sys_name
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	
}
