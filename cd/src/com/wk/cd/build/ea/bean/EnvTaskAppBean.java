/**
 * Title: EnvTaskAppBean.java
 * File Description: ��������ӿڣ�App��
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��28��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: ��������ӿڣ�App��
 * @author Xul
 */
public class EnvTaskAppBean extends EnvTaskInfo{
	
	private static final long serialVersionUID = -8454320284284666489L;
	
	/**
	 *��������
	 */
	private String program_name;

	public static final String PROGRAM_NAMECN = "��������";
	
	/**
	 *��Ŀ���
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "��Ŀ���";
	
	/**
	 *����ʱ��
	 */
	private JaDateTime createDateTime;
	
	public static final String CREATEDATETIMECN = "����ʱ��";
	
	/**
	 * ������������
	 */
	private String crt_user_cname;
	
	public static final String CRT_USER_CNAMECN = "������������";
	
	/**
	 * ִ����������
	 */
	private String exe_user_cname;
	
	public static final String EXE_USER_CNAMECN = "ִ����������";

	/**
	 * @return program_name ��������
	 */
	public String getProgram_name() {
		return program_name;
	}

	/**
	 * @param program_name ��������
	 */
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	/**
	 * @return project_short_name ��Ŀ���
	 */
	public String getProject_short_name() {
		return project_short_name;
	}

	/**
	 * @param project_short_name ��Ŀ���
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 * @return createDateTime ����ʱ��
	 */
	public JaDateTime getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime ����ʱ��
	 */
	public void setCreateDateTime(JaDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	/**
	 * @return crt_user_cname ������������
	 */
	public String getCrt_user_cname() {
		return crt_user_cname;
	}

	/**
	 * @param crt_user_cname ������������
	 */
	public void setCrt_user_cname(String crt_user_cname) {
		this.crt_user_cname = crt_user_cname;
	}

	/**
	 * @return exe_user_cname ִ����������
	 */
	public String getExe_user_cname() {
		return exe_user_cname;
	}

	/**
	 * @param exe_user_cname ִ����������
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
	
	//��ȡJaDateTime
	private static JaDateTime getJaDateTime(JaDate d, JaTime t){
		if(Assert.isEmpty(d) || Assert.isEmpty(t)){
			return null;
		}
		JaDateTime dt = JaDateTime.valueOf(d.getYear(),d.getMonth(),d.getDay(),t.getHour(),t.getMinute(),t.getSecond(),t.getMillisecond());
		return dt;
	}
}
