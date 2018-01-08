/**
 * Title: DeleteConfigFileInputBean.java
 * File Description: 删除构建配置文件服务输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.YN_FLAG;

/**
 * Class Description: 删除构建配置文件服务输入接口
 * @author chiss
 */
public class DeleteBuildConfigInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 1652350278350706585L;
	
	/**
	 * 环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";
	
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
	 * 任务编号
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "任务编号";
	
	/**
	 * 文件相对路径
	 */
	private String relative_path;
	
	public static final String RELATIVE_PATHCN = "文件相对路径";
	
	/**
	 *是否目录标志
	 */
	private YN_FLAG dir_yn_flag;

	public static final String DIR_YN_FLAGCN = "是否目录标志";
	
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
	 * @return work_id 任务编号
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
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
	 * @return dir_yn_flag 是否目录标志
	 */
	public YN_FLAG getDir_yn_flag() {
		return dir_yn_flag;
	}

	/**
	 * @param dir_yn_flag 是否目录标志
	 */
	public void setDir_yn_flag(YN_FLAG dir_yn_flag) {
		this.dir_yn_flag = dir_yn_flag;
	}
}
