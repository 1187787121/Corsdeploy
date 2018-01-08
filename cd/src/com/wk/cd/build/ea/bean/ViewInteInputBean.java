/**
 * Title: ViewInteInputBean.java
 * File Description: 集成任务查看服务输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 集成任务查看服务输入接口
 * @author Xul
 */
public class ViewInteInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -2715713132973262480L;
	
	/**
	 * 任务编号(可能包含入库编号)
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";
	
	/**
	 * 任务全类型
	 */
	private TASK_ALL_TYPE task_all_type;
	
	public static final String TASK_ALL_TYPECN = "任务全类型";
	
	/**
	 * 包名列表
	 */
	private String[] pac_list;
	
	public static final String PAC_LISTCN = "包名列表";
	
	/**
	 * 环境名
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名";
	
	/**
	 * 方案编号
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "方案编号";
	
	/**
	 *版本环境源码版本号
	 */
	private String vercode_ver_num;

	public static final String VERCODE_VER_NUMCN = "版本环境源码版本号";
	
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
	 * @return task_all_type 任务全类型
	 */
	public TASK_ALL_TYPE getTask_all_type() {
		return task_all_type;
	}

	/**
	 * @param task_all_type 任务全类型
	 */
	public void setTask_all_type(TASK_ALL_TYPE task_all_type) {
		this.task_all_type = task_all_type;
	}

	/**
	 * @return pac_list 包名列表
	 */
	public String[] getPac_list() {
		return pac_list;
	}

	/**
	 * @param pac_list 包名列表
	 */
	public void setPac_list(String[] pac_list) {
		this.pac_list = pac_list;
	}

	/**
	 * @return env_name 环境名
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name 环境名
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return prog_id 方案编号
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id 方案编号
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
	
	/**
	 *@return vercode_ver_num 版本环境源码版本号
	 */
	public String getVercode_ver_num() {
		return this.vercode_ver_num;
	}

	/**
	 *@param vercode_ver_num 版本环境源码版本号
	 */
	public void setVercode_ver_num(String vercode_ver_num) {
		this.vercode_ver_num = vercode_ver_num;
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
}
