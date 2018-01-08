/**
 * Title: EnvModFileBean.java
 * File Description: 环境配置文件接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月18日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.build.ea.info.EnvConfigfileModInfo;

/**
 * Class Description: 环境配置文件接口
 * @author Xul
 */
public class EnvModFileBean extends EnvConfigfileModInfo{

	private static final long serialVersionUID = 7560931516821622383L;
	
	/**
	 * 文件全路径
	 */
	private String full_path;
	
	public static final String FULL_PATHCN = "文件全路径";
	
	/**
	 * 修改且删除标志
	 */
	private boolean del_mod_flag;
	
	public static final String DEL_MOD_FLAGCN = "修改且删除标志";

	/**
	 * @return full_path 文件全路径
	 */
	public String getFull_path() {
		return full_path;
	}

	/**
	 * @param full_path 文件全路径
	 */
	public void setFull_path(String full_path) {
		this.full_path = full_path;
	}

	/**
	 * @return del_mod_flag 修改且删除标志
	 */
	public boolean isDel_mod_flag() {
		return del_mod_flag;
	}

	/**
	 * @param del_mod_flag 修改且删除标志
	 */
	public void setDel_mod_flag(boolean del_mod_flag) {
		this.del_mod_flag = del_mod_flag;
	}
}
