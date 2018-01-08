/**
 * Title: EnvTaskAppBean.java
 * File Description: 环境任务接口（App）
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 环境任务接口（App）
 * @author Xul
 */
public class EnvTaskAppBean extends EnvTaskInfo{
	
	private static final long serialVersionUID = -8454320284284666489L;
	
	/**
	 *方案名称
	 */
	private String program_name;

	public static final String PROGRAM_NAMECN = "方案名称";
	
	/**
	 *项目简称
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "项目简称";
	
	/**
	 *创建时间
	 */
	private JaDateTime createDateTime;
	
	public static final String CREATEDATETIMECN = "创建时间";
	
	/**
	 * 创建人中文名
	 */
	private String crt_user_cname;
	
	public static final String CRT_USER_CNAMECN = "创建人中文名";
	
	/**
	 * 执行人中文名
	 */
	private String exe_user_cname;
	
	public static final String EXE_USER_CNAMECN = "执行人中文名";

	/**
	 * @return program_name 方案名称
	 */
	public String getProgram_name() {
		return program_name;
	}

	/**
	 * @param program_name 方案名称
	 */
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
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
	 * @return createDateTime 创建时间
	 */
	public JaDateTime getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime 创建时间
	 */
	public void setCreateDateTime(JaDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	/**
	 * @return crt_user_cname 创建人中文名
	 */
	public String getCrt_user_cname() {
		return crt_user_cname;
	}

	/**
	 * @param crt_user_cname 创建人中文名
	 */
	public void setCrt_user_cname(String crt_user_cname) {
		this.crt_user_cname = crt_user_cname;
	}

	/**
	 * @return exe_user_cname 执行人中文名
	 */
	public String getExe_user_cname() {
		return exe_user_cname;
	}

	/**
	 * @param exe_user_cname 执行人中文名
	 */
	public void setExe_user_cname(String exe_user_cname) {
		this.exe_user_cname = exe_user_cname;
	}

	/** 
	 * Description: 
	 * @param info
	 * @return 
	 */
	public static EnvTaskAppBean copy(EnvTaskInfo info) {
		EnvTaskAppBean bean = new EnvTaskAppBean();
		bean.setWork_id(info.getWork_id());
		bean.setTask_type(info.getTask_type());
		bean.setTask_status(info.getTask_status());
		bean.setCreateDateTime(getJaDateTime(info.getCrt_bk_date(), info.getCrt_bk_time()));
		bean.setCrt_user_id(info.getCrt_user_id());
		bean.setExe_user_id(info.getExe_user_id());
		bean.setStart_bk_tm(info.getStart_bk_tm());
		bean.setEnd_bk_tm(info.getEnd_bk_tm());
		bean.setEnv_name(info.getEnv_name());
		bean.setTask_bk_desc(info.getTask_bk_desc());
		bean.setProject_name(info.getProject_name());
		bean.setProg_id(info.getProg_id());
		if(TASK_TYPE.INTEGRATION.equals(info.getTask_type())){
			bean.setVercode_ver_num(info.getVercode_ver_num());
		}else if(TASK_TYPE.PUBLISH.equals(info.getTask_type())){
			bean.setVercode_ver_num(info.getTarget_ver_num());
		}
		bean.setTime_used(info.getTime_used());
		bean.setExe_result(info.getExe_result());
		return bean;
	}
	
	//获取JaDateTime
	private static JaDateTime getJaDateTime(JaDate d, JaTime t){
		if(Assert.isEmpty(d) || Assert.isEmpty(t)){
			return null;
		}
		JaDateTime dt = JaDateTime.valueOf(d.getYear(),d.getMonth(),d.getDay(),t.getHour(),t.getMinute(),t.getSecond(),t.getMillisecond());
		return dt;
	}
}
