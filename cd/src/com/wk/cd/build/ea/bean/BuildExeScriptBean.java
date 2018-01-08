/**
 * Title: BuildExeScriptBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年12月16日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.module1.info.ParamInfo;

/**
 * Class Description: 构建信息
 * @author xuph
 */
public class BuildExeScriptBean {
	/**
	 * 任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";
	/**
	 * 组件ID
	 */
	private String id;

	public static final String IDCN = "组件ID";

	/**
	 * 组件中文名
	 */
	private String cn_name;

	public static final String CN_NAMECN = "组件中文名";

	/**
	 * 脚本类型
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "脚本类型";

	/**
	 * 脚本方式
	 */
	private SCRIPT_METHOD script_method;

	public static final String SCRIPT_METHODCN = "脚本方式";

	/**
	 * 服务数据源列表
	 */
	private List<UuSocInfo> soc_list;

	public static final String SOC_LISTCN = "服务数据源列表";

	/**
	 * 脚本命令
	 */
	private String[] script_text;

	public static final String SCRIPT_TEXTCN = "脚本命令";

	/**
	 * 参数信息
	 */
	private ParamInfo[] param_list;

	public static final String PARAM_LISTCN = "参数信息";
	
	/**
	 * 执行结果
	 */
	private EXE_RESULT exe_result;
	
	public static final String EXE_RESULTCN  = "执行结果";
	
	/**
	 * 脚本序号
	 */
	private long script_bk_seq;
	
	public static final String SCRIPT_BK_SEQCN = "脚本序号";
	
	/**
	 * 总阶段数
	 */
	private int all_phases;
	
	public static final String ALL_PHASESCN = "总阶段数";
	
	/**
	 * 当前阶段数
	 */
	private int exe_phase;
	
	public static final String EXE_PHASECN = "当前阶段数";

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
	 * @return script_type 脚本类型
	 */
	public SCRIPT_TYPE getScript_type() {
		return this.script_type;
	}

	/**
	 * @param script_type 脚本类型
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}

	/**
	 * @return script_method 脚本方式
	 */
	public SCRIPT_METHOD getScript_method() {
		return this.script_method;
	}

	/**
	 * @param script_method 脚本方式
	 */
	public void setScript_method(SCRIPT_METHOD script_method) {
		this.script_method = script_method;
	}

	/**
	 * @return comp_id 组件ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param comp_id 组件ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return comp_cn_name 组件中文名
	 */
	public String getCn_name() {
		return this.cn_name;
	}

	/**
	 * @param comp_cn_name 组件中文名
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}

	/**
	 * @return script_text 脚本命令
	 */
	public String[] getScript_text() {
		return this.script_text;
	}

	/**
	 * @param script_text 脚本命令
	 */
	public void setScript_text(String[] script_text) {
		this.script_text = script_text;
	}

	/**
	 * @return soc_list 服务数据源列表
	 */
	public List<UuSocInfo> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list 服务数据源列表
	 */
	public void setSoc_list(List<UuSocInfo> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return param_list 参数信息
	 */
	public ParamInfo[] getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list 参数信息
	 */
	public void setParam_list(ParamInfo[] param_list) {
		this.param_list = param_list;
	}

	/**
	 * @return exe_result
	 */
	public EXE_RESULT getExe_result() {
		return exe_result;
	}

	/**
	 * @param exe_result
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/**
	 * @return script_bk_seq
	 */
	public long getScript_bk_seq() {
		return script_bk_seq;
	}

	/**
	 * @param script_bk_seq
	 */
	public void setScript_bk_seq(long script_bk_seq) {
		this.script_bk_seq = script_bk_seq;
	}

	/**
	 * @return all_phases
	 */
	public int getAll_phases() {
		return all_phases;
	}

	/**
	 * @param all_phases
	 */
	public void setAll_phases(int all_phases) {
		this.all_phases = all_phases;
	}

	/**
	 * @return exe_phase
	 */
	public int getExe_phase() {
		return exe_phase;
	}

	/**
	 * @param exe_phase
	 */
	public void setExe_phase(int exe_phase) {
		this.exe_phase = exe_phase;
	}
	
}
