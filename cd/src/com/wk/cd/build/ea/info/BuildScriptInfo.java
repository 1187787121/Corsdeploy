/**
 * Title: BuildScriptInfo.java
 * File Description: 构建脚本信息表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDateTime;
/**
 * Class description:构建脚本信息表
 * @author AutoGen
 */
@Table("BUILD_SCRIPT")
@PrimaryKey({"work_id","script_type","scirpt_bk_seq"})
public class BuildScriptInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "构建脚本信息表";

	/**
	 *任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";

	/**
	 *脚本类型
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "脚本类型";

	/**
	 *脚本序号
	 */
	private long scirpt_bk_seq;

	public static final String SCIRPT_BK_SEQCN = "脚本序号";

	/**
	 *脚本方式
	 */
	private SCRIPT_METHOD script_method;

	public static final String SCRIPT_METHODCN = "脚本方式";

	/**
	 *组件ID
	 */
	private String module_id;

	public static final String MODULE_IDCN = "组件ID";

	/**
	 *组件中文名
	 */
	private String module_cn_name;

	public static final String MODULE_CN_NAMECN = "组件中文名";

	/**
	 *数据源UUID
	 */
	private String soc_uuid;

	public static final String SOC_UUIDCN = "数据源UUID";

	/**
	 *组件参数表UUID
	 */
	private String module_param_uuid;

	public static final String MODULE_PARAM_UUIDCN = "组件参数表UUID";

	/**
	 *实例ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "实例ID";

	/**
	 *脚本命令
	 */
	private String script_text;

	public static final String SCRIPT_TEXTCN = "脚本命令";

	/**
	 *执行状态
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "执行状态";

	/**
	 *执行结果
	 */
	private EXE_RESULT exe_result;

	public static final String EXE_RESULTCN = "执行结果";

	/**
	 *执行日志
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "执行日志";

	/**
	 *执行人
	 */
	private String exe_user_id;

	public static final String EXE_USER_IDCN = "执行人";

	/**
	 *执行开始时间
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "执行开始时间";

	/**
	 *执行结束时间
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "执行结束时间";

	/**
	 *耗时
	 */
	private int time_used;

	public static final String TIME_USEDCN = "耗时";

	/**
	 *组件执行类型
	 */
	private IMPL_TYPE impl_type;

	public static final String IMPL_TYPECN = "组件执行类型";

	/**
	 *@return work_id 任务编号
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/**
	 *@param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 *@return script_type 脚本类型
	 */
	public SCRIPT_TYPE getScript_type() {
		return this.script_type;
	}

	/**
	 *@param script_type 脚本类型
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}

	/**
	 *@return scirpt_bk_seq 脚本序号
	 */
	public long getScirpt_bk_seq() {
		return this.scirpt_bk_seq;
	}

	/**
	 *@param scirpt_bk_seq 脚本序号
	 */
	public void setScirpt_bk_seq(long scirpt_bk_seq) {
		this.scirpt_bk_seq = scirpt_bk_seq;
	}

	/**
	 *@return script_method 脚本方式
	 */
	public SCRIPT_METHOD getScript_method() {
		return this.script_method;
	}

	/**
	 *@param script_method 脚本方式
	 */
	public void setScript_method(SCRIPT_METHOD script_method) {
		this.script_method = script_method;
	}

	/**
	 *@return module_id 组件ID
	 */
	public String getModule_id() {
		return this.module_id;
	}

	/**
	 *@param module_id 组件ID
	 */
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	/**
	 *@return module_cn_name 组件中文名
	 */
	public String getModule_cn_name() {
		return this.module_cn_name;
	}

	/**
	 *@param module_cn_name 组件中文名
	 */
	public void setModule_cn_name(String module_cn_name) {
		this.module_cn_name = module_cn_name;
	}

	/**
	 *@return soc_uuid 数据源UUID
	 */
	public String getSoc_uuid() {
		return this.soc_uuid;
	}

	/**
	 *@param soc_uuid 数据源UUID
	 */
	public void setSoc_uuid(String soc_uuid) {
		this.soc_uuid = soc_uuid;
	}

	/**
	 *@return module_param_uuid 组件参数表UUID
	 */
	public String getModule_param_uuid() {
		return this.module_param_uuid;
	}

	/**
	 *@param module_param_uuid 组件参数表UUID
	 */
	public void setModule_param_uuid(String module_param_uuid) {
		this.module_param_uuid = module_param_uuid;
	}

	/**
	 *@return instance_id 实例ID
	 */
	public String getInstance_id() {
		return this.instance_id;
	}

	/**
	 *@param instance_id 实例ID
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	/**
	 *@return script_text 脚本命令
	 */
	public String getScript_text() {
		return this.script_text;
	}

	/**
	 *@param script_text 脚本命令
	 */
	public void setScript_text(String script_text) {
		this.script_text = script_text;
	}

	/**
	 *@return exe_status 执行状态
	 */
	public EXE_STATUS getExe_status() {
		return this.exe_status;
	}

	/**
	 *@param exe_status 执行状态
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 *@return exe_result 执行结果
	 */
	public EXE_RESULT getExe_result() {
		return this.exe_result;
	}

	/**
	 *@param exe_result 执行结果
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/**
	 *@return exelog_bk_path 执行日志
	 */
	public String getExelog_bk_path() {
		return this.exelog_bk_path;
	}

	/**
	 *@param exelog_bk_path 执行日志
	 */
	public void setExelog_bk_path(String exelog_bk_path) {
		this.exelog_bk_path = exelog_bk_path;
	}

	/**
	 *@return exe_user_id 执行人
	 */
	public String getExe_user_id() {
		return this.exe_user_id;
	}

	/**
	 *@param exe_user_id 执行人
	 */
	public void setExe_user_id(String exe_user_id) {
		this.exe_user_id = exe_user_id;
	}

	/**
	 *@return start_bk_tm 执行开始时间
	 */
	public JaDateTime getStart_bk_tm() {
		return this.start_bk_tm;
	}

	/**
	 *@param start_bk_tm 执行开始时间
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 *@return end_bk_tm 执行结束时间
	 */
	public JaDateTime getEnd_bk_tm() {
		return this.end_bk_tm;
	}

	/**
	 *@param end_bk_tm 执行结束时间
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 *@return time_used 耗时
	 */
	public int getTime_used() {
		return this.time_used;
	}

	/**
	 *@param time_used 耗时
	 */
	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}

	/**
	 *@return impl_type 组件执行类型
	 */
	public IMPL_TYPE getImpl_type() {
		return this.impl_type;
	}

	/**
	 *@param impl_type 组件执行类型
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

}
