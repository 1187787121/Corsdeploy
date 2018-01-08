/**
 * Title: ViewConfigModInputBean.java
 * File Description: 修改配置文件相关服务输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改配置文件相关服务输入接口
 * @author Xul
 */
public class ViewConfigModInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 4681481104565545315L;
	
	/**
	 * 环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 批次号
	 */
	private String batch_no;

	public static final String BATCH_NOCN = "批次号";
	
	/**
	 * 服务器名称
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "服务器名称";
	
	/**
	 * 数据源名称
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "数据源名称";
	
	/**
	 * 文件相对路径
	 */
	private String relative_path;
	
	public static final String RELATIVE_PATHCN = "文件相对路径";
	
	/**
	 * 服务器地址
	 */
	private String server_ip;

	public static final String SERVER_IPCN = "服务器地址";
	
	/**
	 * 文件可编辑标志
	 */
	private boolean mod_flag;
	
	public static final String MOD_FLAGCN = "文件可编辑标志";
	
	/**
	 * 配置类型
	 */
	private CFG_TYPE cfg_type;
	
	public static final String CFG_TYPECN = "配置类型";

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

	/**
	 * @return batch_no 批次号
	 */
	public String getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no 批次号
	 */
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	/**
	 * @return ce_server_name 服务器名称
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name 服务器名称
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return soc_name 数据源名称
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name 数据源名称
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return relative_path 文件相对路径
	 */
	public String getRelative_path() {
		return relative_path;
	}

	/**
	 * @param relative_path 文件相对路径
	 */
	public void setRelative_path(String relative_path) {
		this.relative_path = relative_path;
	}

	/**
	 * @return server_ip 服务器地址
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param server_ip 服务器地址
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return mod_flag 文件可编辑标志
	 */
	public boolean isMod_flag() {
		return mod_flag;
	}

	/**
	 * @param mod_flag 文件可编辑标志
	 */
	public void setMod_flag(boolean mod_flag) {
		this.mod_flag = mod_flag;
	}

	/**
	 * @return cfg_type
	 */
	public CFG_TYPE getCfg_type() {
		return cfg_type;
	}

	/**
	 * @param cfg_type
	 */
	public void setCfg_type(CFG_TYPE cfg_type) {
		this.cfg_type = cfg_type;
	}
}
