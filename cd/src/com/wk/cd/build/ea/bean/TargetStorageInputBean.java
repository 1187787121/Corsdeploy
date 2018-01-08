/**
 * Title: TargetStorageInputBean.java
 * File Description: 目标入库服务输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 目标入库服务输入接口
 * @author Xul
 */
public class TargetStorageInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -6473768967455949639L;
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 入库描述
	 */
	private String storage_bk_desc;

	public static final String STORAGE_BK_DESCCN = "入库描述";

	/**
	 * 项目编号
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "项目编号";
	
	/**
	 * 服务器名
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "服务器名";
	
	/**
	 * 数据源名
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "数据源名";
	
	/**
	 * 目标版本服务器名
	 */
	private String tag_server_name;
	
	public static final String TAG_SERVER_NAMECN = "目标版本服务器名";
	
	/**
	 * 目标版本数据源名
	 */
	private String tag_soc_name;
	
	public static final String TAG_SOC_NAMECN = "目标版本数据源名";
	
	/**
	 * 目标版本目录
	 */
	private String tag_bk_dir;
	
	public static final String TAG_BK_DIRCN = "目标版本目录";
	
	/**
	 * 目标包列表
	 */
	private List<TargetPackageBean> tar_package_list;
	
	public static final String TAR_PACKAGE_LISTCN = "目标包列表";
	
	private String inte_ver_num;
	
	public static final String INTE_VER_NUMCN = "集成版本号";
	
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
	 * @return storage_bk_desc 入库描述
	 */
	public String getStorage_bk_desc() {
		return storage_bk_desc;
	}

	/**
	 * @param storage_bk_desc 入库描述
	 */
	public void setStorage_bk_desc(String storage_bk_desc) {
		this.storage_bk_desc = storage_bk_desc;
	}

	/**
	 * @return project_name 项目编号
	 */
	public String getProject_name() {
		return project_name;
	}

	/**
	 * @param project_name 项目编号
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 * @return ce_server_name 服务器名
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name 服务器名
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return soc_name 数据源名
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name 数据源名
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return tag_server_name 目标版本服务器名
	 */
	public String getTag_server_name() {
		return tag_server_name;
	}

	/**
	 * @param tag_server_name 目标版本服务器名
	 */
	public void setTag_server_name(String tag_server_name) {
		this.tag_server_name = tag_server_name;
	}

	/**
	 * @return tag_soc_name 目标版本数据源名
	 */
	public String getTag_soc_name() {
		return tag_soc_name;
	}

	/**
	 * @param tag_soc_name 目标版本数据源名
	 */
	public void setTag_soc_name(String tag_soc_name) {
		this.tag_soc_name = tag_soc_name;
	}

	/**
	 * @return tag_bk_dir 目标版本目录
	 */
	public String getTag_bk_dir() {
		return tag_bk_dir;
	}

	/**
	 * @param tag_bk_dir 目标版本目录
	 */
	public void setTag_bk_dir(String tag_bk_dir) {
		this.tag_bk_dir = tag_bk_dir;
	}

	/**
	 * @return tar_package_list 目标包列表
	 */
	public List<TargetPackageBean> getTar_package_list() {
		return tar_package_list;
	}

	/**
	 * @param tar_package_list 目标包列表
	 */
	public void setTar_package_list(List<TargetPackageBean> tar_package_list) {
		this.tar_package_list = tar_package_list;
	}

	/**
	 * @return inte_ver_num 集成版本号
	 */
	public String getInte_ver_num() {
		return inte_ver_num;
	}

	/**
	 * @param inte_ver_num 集成版本号
	 */
	public void setInte_ver_num(String inte_ver_num) {
		this.inte_ver_num = inte_ver_num;
	}
	
	
}
