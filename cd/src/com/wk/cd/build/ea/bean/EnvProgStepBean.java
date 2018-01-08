/**
 * Title: EnvProgStepBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.enu.COMPILE_TYPE;
import com.wk.cd.enu.STEP_TYPE;

/**
 * Class Description:方案步骤
 * @author xuph
 */
public class EnvProgStepBean {
	/**
	 * 步骤编号
	 */
	private int step_id;
	
	public static final String STEP_IDCN = "步骤编号";
	
	/**
	 * 步骤说明
	 */
	private String step_expl;

	public static final String STEP_EXPLCN = "步骤说明";

	/**
	 * 步骤类型
	 */
	private STEP_TYPE step_type;

	public static final String STEP_TYPECN = "步骤类型";

	/**
	 * 数据源
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "数据源";

	/**
	 * 服务器名
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务器名";
	/**
	 * 源码版本数据源名
	 */
	private String code_soc_name;

	public static final String CODE_SOC_NAMECN = "源码版本数据源名";
	/**
	 * 源码版本服务器名
	 */
	private String code_server_name;

	public static final String CODE_SERVER_NAMECN = "源码版本服务器名";
	/**
	 * 源码版本目录
	 */
	private String code_bk_dir;

	public static final String CODE_BK_DIRCN = "源码版本目录";

	/**
	 * 脚本
	 */
	private String step_bk_script;

	public static final String STEP_BK_SCRIPTCN = "脚本";

	/**
	 * 编译类型
	 */
	private COMPILE_TYPE compile_type;

	public static final String COMPILE_TYPECN = "编译类型";
	/**
	 * 环境变量
	 */
	private String env_variable;

	public static final String ENV_VARIABLECN = "环境变量";
	/**
	 * 编译参数
	 */
	private String compile_param;

	public static final String COMPILE_PARAMCN = "编译参数";

	/**
	 * 目标版本数据源
	 */
	private String tar_soc_name;

	public static final String TAR_SOC_NAMECN = "目标版本数据源";
	/**
	 * 目标版本服务器名
	 */
	private String tar_server_name;

	public static final String TAR_SERVER_NAMECN = "目标版本服务器名";
	/**
	 * 目标版本目录
	 */
	private String tar_bk_dir;

	public static final String TAR_BK_DIRCN = "目标版本目录";
	
	/**
	 * 目标包列表
	 */
	private List<TargetPackageBean> tar_package_list;
	
	public static final String TAR_PACKAGE_LISTCN = "目标包列表";
	
	/**
	 *编译路径
	 */
	private String complie_bk_path;

	public static final String COMPLIE_BK_PATHCN = "编译路径";
	
	/**
	 * 打包根目录
	 */
	private String target_root_path;
	
	public static final String TARGET_ROOT_PATHCN = "打包根目录";

	/**
	 * @return step_id 步骤编号
	 */
	public int getStep_id() {
		return step_id;
	}

	/**
	 * @param step_id 步骤编号
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	/**
	 * @return step_expl
	 */
	public String getStep_expl() {
		return step_expl;
	}

	/**
	 * @param step_expl
	 */
	public void setStep_expl(String step_expl) {
		this.step_expl = step_expl;
	}

	/**
	 * @return step_type
	 */
	public STEP_TYPE getStep_type() {
		return step_type;
	}

	/**
	 * @param step_type
	 */
	public void setStep_type(STEP_TYPE step_type) {
		this.step_type = step_type;
	}

	/**
	 * @return soc_name
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return server_name
	 */
	public String getServer_name() {
		return server_name;
	}

	/**
	 * @param server_name
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 * @return code_soc_name
	 */
	public String getCode_soc_name() {
		return code_soc_name;
	}

	/**
	 * @param code_soc_name
	 */
	public void setCode_soc_name(String code_soc_name) {
		this.code_soc_name = code_soc_name;
	}

	/**
	 * @return code_server_name
	 */
	public String getCode_server_name() {
		return code_server_name;
	}

	/**
	 * @param code_server_name
	 */
	public void setCode_server_name(String code_server_name) {
		this.code_server_name = code_server_name;
	}

	/**
	 * @return code_bk_dir
	 */
	public String getCode_bk_dir() {
		return code_bk_dir;
	}

	/**
	 * @param code_bk_dir
	 */
	public void setCode_bk_dir(String code_bk_dir) {
		this.code_bk_dir = code_bk_dir;
	}

	/**
	 * @return step_bk_script
	 */
	public String getStep_bk_script() {
		return step_bk_script;
	}

	/**
	 * @param step_bk_script
	 */
	public void setStep_bk_script(String step_bk_script) {
		this.step_bk_script = step_bk_script;
	}

	/**
	 * @return compile_type
	 */
	public COMPILE_TYPE getCompile_type() {
		return compile_type;
	}

	/**
	 * @param compile_type
	 */
	public void setCompile_type(COMPILE_TYPE compile_type) {
		this.compile_type = compile_type;
	}

	/**
	 * @return env_variable
	 */
	public String getEnv_variable() {
		return env_variable;
	}

	/**
	 * @param env_variable
	 */
	public void setEnv_variable(String env_variable) {
		this.env_variable = env_variable;
	}

	/**
	 * @return compile_param
	 */
	public String getCompile_param() {
		return compile_param;
	}

	/**
	 * @param compile_param
	 */
	public void setCompile_param(String compile_param) {
		this.compile_param = compile_param;
	}

	/**
	 * @return tar_soc_name
	 */
	public String getTar_soc_name() {
		return tar_soc_name;
	}

	/**
	 * @param tar_soc_name
	 */
	public void setTar_soc_name(String tar_soc_name) {
		this.tar_soc_name = tar_soc_name;
	}

	/**
	 * @return tar_server_name
	 */
	public String getTar_server_name() {
		return tar_server_name;
	}

	/**
	 * @param tar_server_name
	 */
	public void setTar_server_name(String tar_server_name) {
		this.tar_server_name = tar_server_name;
	}

	/**
	 * @return tar_bk_dir
	 */
	public String getTar_bk_dir() {
		return tar_bk_dir;
	}

	/**
	 * @param tar_bk_dir
	 */
	public void setTar_bk_dir(String tar_bk_dir) {
		this.tar_bk_dir = tar_bk_dir;
	}

	/**
	 * @return tar_package_list
	 */
	public List<TargetPackageBean> getTar_package_list() {
		return tar_package_list;
	}

	/**
	 * @param tar_package_list
	 */
	public void setTar_package_list(List<TargetPackageBean> tar_package_list) {
		this.tar_package_list = tar_package_list;
	}
	
	/**
	 * @return complie_bk_path 编译路径
	 */
	public String getComplie_bk_path() {
		return complie_bk_path;
	}

	/**
	 * @param complie_bk_path 编译路径
	 */
	public void setComplie_bk_path(String complie_bk_path) {
		this.complie_bk_path = complie_bk_path;
	}

	
	
	/**
	 * @return target_root_path 打包根目录
	 */
	public String getTarget_root_path() {
		return target_root_path;
	}

	/**
	 * @param target_root_path 打包根目录
	 */
	public void setTarget_root_path(String target_root_path) {
		this.target_root_path = target_root_path;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "EnvProgStepBean [step_id=" + step_id + ", step_expl="
				+ step_expl + ", step_type=" + step_type + ", soc_name="
				+ soc_name + ", server_name=" + server_name
				+ ", code_soc_name=" + code_soc_name + ", code_server_name="
				+ code_server_name + ", code_bk_dir=" + code_bk_dir
				+ ", step_bk_script=" + step_bk_script + ", compile_type="
				+ compile_type + ", env_variable=" + env_variable
				+ ", compile_param=" + compile_param + ", tar_soc_name="
				+ tar_soc_name + ", tar_server_name=" + tar_server_name
				+ ", tar_bk_dir=" + tar_bk_dir + ", tar_package_list="
				+ tar_package_list + "]";
	}

}
