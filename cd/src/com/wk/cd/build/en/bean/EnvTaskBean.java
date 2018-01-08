/**
 * Title: EnvTaskBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��26��
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;

/**
 * Class Description: 
 * @author chiss
 */
public class EnvTaskBean {
	/**
	 * ��������
	 */
	private TASK_TYPE task_type;
	
	public static final String task_typecn = "��������";
	
	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
	/**
	 * �汾��
	 */
	private String code_ver_num;
	
	public static final String CODE_VER_NUMCN = "�汾��";
	
	/**
	 * �����б�
	 */
	private List<String> package_list;
	
	public static final String PACKAGE_LISTCN = "�����б�";
	
	/**
	 * ����״̬
	 */
	private TASK_STATUS task_status;
	
	public static final String TASK_STATUSCN = "����״̬";
	
	/**
	 * ִ�н��
	 */
	private EXE_RESULT exe_result;
	
	public static final String EXE_RESULTCN = "ִ�н��";

	/**
	 * @return task_type ��������
	 */
	public TASK_TYPE getTask_type() {
		return task_type;
	}

	/**
	 * @param task_type ��������
	 */
	public void setTask_type(TASK_TYPE task_type) {
		this.task_type = task_type;
	}

	/**
	 * @return work_id ������ 
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id ������ 
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return code_ver_num �汾��
	 */
	public String getCode_ver_num() {
		return code_ver_num;
	}

	/**
	 * @param code_ver_num �汾��
	 */
	public void setCode_ver_num(String code_ver_num) {
		this.code_ver_num = code_ver_num;
	}

	/**
	 * @return package_list �����б�
	 */
	public List<String> getPackage_list() {
		return package_list;
	}

	/**
	 * @param package_list �����б�
	 */
	public void setPackage_list(List<String> package_list) {
		this.package_list = package_list;
	}

	/**
	 * @return task_status ����״̬
	 */
	public TASK_STATUS getTask_status() {
		return task_status;
	}

	/**
	 * @param task_status ����״̬
	 */
	public void setTask_status(TASK_STATUS task_status) {
		this.task_status = task_status;
	}

	/**
	 * @return exe_result ִ�н��
	 */
	public EXE_RESULT getExe_result() {
		return exe_result;
	}

	/**
	 * @param exe_result ִ�н��
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "EnvTaskBean [task_type=" + task_type + ", work_id=" + work_id
				+ ", env_name=" + env_name + ", code_ver_num=" + code_ver_num
				+ ", package_list=" + package_list + ", task_status="
				+ task_status + ", exe_result=" + exe_result + "]";
	}
}
