/**
 * Title: EnvTaskInfo.java
 * File Description: 环境任务表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-6
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;
import com.wk.util.*;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:环境任务表
 * @author AutoGen
 */
@Table("ENV_TASK")
@PrimaryKey({"work_id"})
public class EnvTaskInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "环境任务表";

	/**
	 *任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";

	/**
	 *任务类型
	 */
	private TASK_TYPE task_type;

	public static final String TASK_TYPECN = "任务类型";

	/**
	 *任务描述
	 */
	private String task_bk_desc;

	public static final String TASK_BK_DESCCN = "任务描述";

	/**
	 *回退任务编号
	 */
	private String rol_work_id;

	public static final String ROL_WORK_IDCN = "回退任务编号";

	/**
	 *所属环境
	 */
	private String env_name;

	public static final String ENV_NAMECN = "所属环境";

	/**
	 *所属项目
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "所属项目";

	/**
	 *方案编号
	 */
	private String prog_id;

	public static final String PROG_IDCN = "方案编号";

	/**
	 *实例ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "实例ID";

	/**
	 *执行动作
	 */
	private EXE_METHOD exe_method;

	public static final String EXE_METHODCN = "执行动作";

	/**
	 *集成目标包清单UUID
	 */
	private String tagpac_list_uuid;

	public static final String TAGPAC_LIST_UUIDCN = "集成目标包清单UUID";

	/**
	 *发布清单UUID
	 */
	private String pub_list_uuid;

	public static final String PUB_LIST_UUIDCN = "发布清单UUID";

	/**
	 *源码版本机版本号
	 */
	private String code_ver_num;

	public static final String CODE_VER_NUMCN = "源码版本机版本号";

	/**
	 *目标版本机版本号
	 */
	private String target_ver_num;

	public static final String TARGET_VER_NUMCN = "目标版本机版本号";

	/**
	 *版本环境源码版本号
	 */
	private String vercode_ver_num;

	public static final String VERCODE_VER_NUMCN = "版本环境源码版本号";

	/**
	 *版本环境目标版本号
	 */
	private String vertarget_ver_num;

	public static final String VERTARGET_VER_NUMCN = "版本环境目标版本号";

	/**
	 *任务状态
	 */
	private TASK_STATUS task_status;

	public static final String TASK_STATUSCN = "任务状态";

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
	 *创建人
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建人";

	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "创建时间";

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
	 *@return task_type 任务类型
	 */
	public TASK_TYPE getTask_type() {
		return this.task_type;
	}

	/**
	 *@param task_type 任务类型
	 */
	public void setTask_type(TASK_TYPE task_type) {
		this.task_type = task_type;
	}

	/**
	 *@return task_bk_desc 任务描述
	 */
	public String getTask_bk_desc() {
		return this.task_bk_desc;
	}

	/**
	 *@param task_bk_desc 任务描述
	 */
	public void setTask_bk_desc(String task_bk_desc) {
		this.task_bk_desc = task_bk_desc;
	}

	/**
	 *@return rol_work_id 回退任务编号
	 */
	public String getRol_work_id() {
		return this.rol_work_id;
	}

	/**
	 *@param rol_work_id 回退任务编号
	 */
	public void setRol_work_id(String rol_work_id) {
		this.rol_work_id = rol_work_id;
	}

	/**
	 *@return env_name 所属环境
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name 所属环境
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return project_name 所属项目
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name 所属项目
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 *@return prog_id 方案编号
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 *@param prog_id 方案编号
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
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
	 *@return exe_method 执行动作
	 */
	public EXE_METHOD getExe_method() {
		return this.exe_method;
	}

	/**
	 *@param exe_method 执行动作
	 */
	public void setExe_method(EXE_METHOD exe_method) {
		this.exe_method = exe_method;
	}

	/**
	 *@return tagpac_list_uuid 集成目标包清单UUID
	 */
	public String getTagpac_list_uuid() {
		return this.tagpac_list_uuid;
	}

	/**
	 *@param tagpac_list_uuid 集成目标包清单UUID
	 */
	public void setTagpac_list_uuid(String tagpac_list_uuid) {
		this.tagpac_list_uuid = tagpac_list_uuid;
	}

	/**
	 *@return pub_list_uuid 发布清单UUID
	 */
	public String getPub_list_uuid() {
		return this.pub_list_uuid;
	}

	/**
	 *@param pub_list_uuid 发布清单UUID
	 */
	public void setPub_list_uuid(String pub_list_uuid) {
		this.pub_list_uuid = pub_list_uuid;
	}

	/**
	 *@return code_ver_num 源码版本机版本号
	 */
	public String getCode_ver_num() {
		return this.code_ver_num;
	}

	/**
	 *@param code_ver_num 源码版本机版本号
	 */
	public void setCode_ver_num(String code_ver_num) {
		this.code_ver_num = code_ver_num;
	}

	/**
	 *@return target_ver_num 目标版本机版本号
	 */
	public String getTarget_ver_num() {
		return this.target_ver_num;
	}

	/**
	 *@param target_ver_num 目标版本机版本号
	 */
	public void setTarget_ver_num(String target_ver_num) {
		this.target_ver_num = target_ver_num;
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
	 *@return vertarget_ver_num 版本环境目标版本号
	 */
	public String getVertarget_ver_num() {
		return this.vertarget_ver_num;
	}

	/**
	 *@param vertarget_ver_num 版本环境目标版本号
	 */
	public void setVertarget_ver_num(String vertarget_ver_num) {
		this.vertarget_ver_num = vertarget_ver_num;
	}

	/**
	 *@return task_status 任务状态
	 */
	public TASK_STATUS getTask_status() {
		return this.task_status;
	}

	/**
	 *@param task_status 任务状态
	 */
	public void setTask_status(TASK_STATUS task_status) {
		this.task_status = task_status;
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
	 *@return crt_user_id 创建人
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id 创建人
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
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

}
