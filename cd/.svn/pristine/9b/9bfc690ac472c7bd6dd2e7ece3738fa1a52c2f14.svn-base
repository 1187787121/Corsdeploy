/**
 * Title: BuildMonPhaseBean.java
 * File Description: 构建应用部署阶段监控
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年12月10日
 */
package com.wk.cd.build.ea.bean;

import java.io.Serializable;
import java.util.List;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PHASE_TYPE;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Script;

/**
 * Class Description: 构建应用部署阶段监控
 * @author Xul
 */
public class BuildMonPhaseBean implements Serializable{

	private static final long serialVersionUID = 8577267008265022209L;
	
	/**
	 * 阶段编号
	 */
	private int phase_id;
	
	public static final String PHASE_IDCN = "阶段编号";
	
	/**
	 * 阶段名字
	 */
    private String phase_name;
    
    public static final String PHASE_NAMECN = "阶段名字";
    
    /**
     * 执行类别
     */
	private IMPL_TYPE impl_type;
	
	public static final String IMPL_TYPECN = "执行类别";
	/**
	 * 执行服务器名
	 */
	private String exe_server_name;
	
	public static final String EXE_SERVER_NAMECN = "执行服务器名";
	
	/**
	 * 版本服务器名
	 */
	private String ver_server_name;
	
	public static final String VER_SERVER_NAMECN = "版本服务器名";
	
	/**
	 * 执行数据源名
	 */
	private String exe_soc_name;

	public static final String EXE_SOC_NAMECN = "执行数据源名";

	/**
	 * 版本数据源
	 */
	private String ver_soc_name;

	public static final String VER_SOC_NAMECN = "版本数据源";
	
    /**
     * 脚本
     */
	private Script script;
	
	public static final String SCRIPT_CN = "脚本";
    
	/**
	 * 执行状态
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "执行状态";
	
	/**
	 * 执行结果
	 */
	private EXE_RESULT task_exe_result;

	public static final String TASK_EXE_RESULTCN = "执行结果";
	
	/**
	 * 实例阶段列表
	 */
	private List<InstPhaseBean> inst_phase_list;
	
	public static final String INST_PHASE_LISTCN = "实例阶段列表";

	/**
	 * @return phase_id
	 */
	public int getPhase_id() {
		return phase_id;
	}

	/**
	 * @param phase_id
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 * @return phase_name
	 */
	public String getPhase_name() {
		return phase_name;
	}

	/**
	 * @param phase_name
	 */
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
	}

	/**
	 * @return impl_type
	 */
	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	/**
	 * @param impl_type
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return exe_server_name
	 */
	public String getExe_server_name() {
		return exe_server_name;
	}

	/**
	 * @param exe_server_name
	 */
	public void setExe_server_name(String exe_server_name) {
		this.exe_server_name = exe_server_name;
	}

	/**
	 * @return ver_server_name
	 */
	public String getVer_server_name() {
		return ver_server_name;
	}

	/**
	 * @param ver_server_name
	 */
	public void setVer_server_name(String ver_server_name) {
		this.ver_server_name = ver_server_name;
	}

	/**
	 * @return exe_soc_name
	 */
	public String getExe_soc_name() {
		return exe_soc_name;
	}

	/**
	 * @param exe_soc_name
	 */
	public void setExe_soc_name(String exe_soc_name) {
		this.exe_soc_name = exe_soc_name;
	}

	/**
	 * @return ver_soc_name
	 */
	public String getVer_soc_name() {
		return ver_soc_name;
	}

	/**
	 * @param ver_soc_name
	 */
	public void setVer_soc_name(String ver_soc_name) {
		this.ver_soc_name = ver_soc_name;
	}

	/**
	 * @return script
	 */
	public Script getScript() {
		return script;
	}

	/**
	 * @param script
	 */
	public void setScript(Script script) {
		this.script = script;
	}

	/**
	 * @return exe_status
	 */
	public EXE_STATUS getExe_status() {
		return exe_status;
	}

	/**
	 * @param exe_status
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 * @return task_exe_result
	 */
	public EXE_RESULT getTask_exe_result() {
		return task_exe_result;
	}

	/**
	 * @param task_exe_result
	 */
	public void setTask_exe_result(EXE_RESULT task_exe_result) {
		this.task_exe_result = task_exe_result;
	}

	/**
	 * @return inst_phase_list
	 */
	public List<InstPhaseBean> getInst_phase_list() {
		return inst_phase_list;
	}

	/**
	 * @param inst_phase_list
	 */
	public void setInst_phase_list(List<InstPhaseBean> inst_phase_list) {
		this.inst_phase_list = inst_phase_list;
	}
}
