/**
 * Title: UpdateUserTempSocPrivInputBean.java
 * File Description: 修改用户临时数据源权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月9日
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;

/**
 * Class Description: 修改用户临时数据源权限输入接口
 * @author HT
 */
public class UpdateUserTempSocPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -2184400699689862542L;
	
	/**
	 * 用户名
	 */
	private String user_id;
	
	public static final String USER_IDCN="用户名";
	
	/**
	 * 数据源权限列表
	 */
	private List<UsUserSocPrivInfo> soc_list;

	public static final String SOC_LISTCN = "数据源权限列表";

	/**
	 * @return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return soc_list 数据源权限列表
	 */
	public List<UsUserSocPrivInfo> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list 数据源权限列表
	 */
	public void setSoc_list(List<UsUserSocPrivInfo> soc_list) {
		this.soc_list = soc_list;
	}
	
}
