/**
 * Title: ViewStorageOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.util.JaDateTime;

/**
 * Class Description: 
 * @author chiss
 */
public class ViewStorageOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : -8685251169673999467L
	 */ 
	private static final long serialVersionUID = -8685251169673999467L;
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
	 * 项目简称
	 */
	private String project_short_name;
	
	public static final String PROJECT_SHORT_NAMECN = "项目简称";
	
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
	/**
	 * 入库状态
	 */
	private STORAGE_STATUS storage_status;
	
	public static final String STORAGE_STATUSCN = "入库状态";
	
	/**
	 * 入库结果
	 */
	private STORAGE_RESULT storage_result;
	
	public static final String STORAGE_RESULTCN = "入库结果";
	
	/**
	 * 入库开始时间
	 */
	private JaDateTime start_bk_tm;
	
	public static final String START_BK_TMCN = "入库开始时间";
	
	/**
	 * 入库结束时间
	 */
	private JaDateTime end_bk_tm;
	
	public static final String END_BK_TMCN = "入库结束时间";
	
	/**
	 * 下载文件列表
	 */
	private List<DownFileBean> down_file_list;
	
	public static final String DOWN_FILE_LISTCN = "下载文件列表";
	
	/**
	 * 总步骤
	 */
	private int total_phase;

	public static final String TOTAL_PHASECN = "总步骤";
	/**
	 * 当前步骤
	 */
	private int current_step;
	
	public static final String CURRENT_STEPCN = "当前步骤";
	
	/**
	 * 当前阶段
	 */
	private int current_phase;
	
	public static final String CURRENT_PHASECN = "当前阶段";
	
	/**
	 * 实例Id
	 */
	private String inst_id;
	
	public static final String INST_IDCN = "实例Id";
	
	/**
     * 打包根路径
     */
    private String storage_bk_path;
    
    public static final String STORAGE_BK_PATHCN = "打包根路径";
    
    /**
     * 版本环境目标版本号
     */
    private String inte_ver_num;

    public static final String INTE_VER_NUMCN = "版本环境目标版本号";
    
    /**
     * 文件清单路径
     */
    private String file_xlsx_path;
    
    public static final String FILE_XLSX_PATHCN = "文件清单路径";
	
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
	 * @return storage_status 入库状态
	 */
	public STORAGE_STATUS getStorage_status() {
		return storage_status;
	}

	/**
	 * @param storage_status 入库状态
	 */
	public void setStorage_status(STORAGE_STATUS storage_status) {
		this.storage_status = storage_status;
	}

	/**
	 * @return storage_result 入库结果
	 */
	public STORAGE_RESULT getStorage_result() {
		return storage_result;
	}

	/**
	 * @param storage_result 入库结果
	 */
	public void setStorage_result(STORAGE_RESULT storage_result) {
		this.storage_result = storage_result;
	}

	/**
	 * @return start_bk_tm 入库开始时间
	 */
	public JaDateTime getStart_bk_tm() {
		return start_bk_tm;
	}

	/**
	 * @param start_bk_tm 入库开始时间
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 * @return end_bk_tm 入库结束时间
	 */
	public JaDateTime getEnd_bk_tm() {
		return end_bk_tm;
	}

	/**
	 * @param end_bk_tm 入库结束时间
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 * @return down_file_list
	 */
	public List<DownFileBean> getDown_file_list() {
		return down_file_list;
	}

	/**
	 * @param down_file_list
	 */
	public void setDown_file_list(List<DownFileBean> down_file_list) {
		this.down_file_list = down_file_list;
	}
	
	/**
	 * 步骤状态
	 */
	private CMD_STATUS cmd_state;
	
	public static final String CMD_STATECN = "步骤状态";

	/**
	 * @return total_phase
	 */
	public int getTotal_phase() {
		return total_phase;
	}

	/**
	 * @param total_step
	 */
	public void setTotal_phase(int total_phase) {
		this.total_phase = total_phase;
	}

	/**
	 * @return current_step
	 */
	public int getCurrent_step() {
		return current_step;
	}

	/**
	 * @param current_step
	 */
	public void setCurrent_step(int current_step) {
		this.current_step = current_step;
	}

	/**
	 * @return cmd_state
	 */
	public CMD_STATUS getCmd_state() {
		return cmd_state;
	}

	/**
	 * @param cmd_state
	 */
	public void setCmd_state(CMD_STATUS cmd_state) {
		this.cmd_state = cmd_state;
	}

	/**
	 * @return current_phase
	 */
	public int getCurrent_phase() {
		return current_phase;
	}

	/**
	 * @param current_phase
	 */
	public void setCurrent_phase(int current_phase) {
		this.current_phase = current_phase;
	}

	/**
	 * @return inst_id 实例ID
	 */
	public String getInst_id() {
		return inst_id;
	}

	/**
	 * @param inst_id 实例ID
	 */
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	/**
	 * @return storage_bk_path 打包根路径
	 */
	public String getStorage_bk_path() {
		return storage_bk_path;
	}

	/**
	 * @param storage_bk_path 打包根路径
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}

	/**
	 * @return inte_ver_num 版本环境目标版本号
	 */
	public String getInte_ver_num() {
		return inte_ver_num;
	}

	/**
	 * @param inte_ver_num 版本环境目标版本号
	 */
	public void setInte_ver_num(String inte_ver_num) {
		this.inte_ver_num = inte_ver_num;
	}

	/**
	 * @return file_xlsx_path 文件清单路径
	 */
	public String getFile_xlsx_path() {
		return file_xlsx_path;
	}

	/**
	 * @param file_xlsx_path 文件清单路径
	 */
	public void setFile_xlsx_path(String file_xlsx_path) {
		this.file_xlsx_path = file_xlsx_path;
	}	
	
}
