/**
 * Title: AddPublishTaskInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class AddPublishTaskInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : -1517775975369341451L
	 */ 
	private static final long serialVersionUID = -1517775975369341451L;
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
	/**
	 * ��������
	 */
	private String task_bk_desc ;
	
	public static final String TASK_BK_DESCCN = "��������";
	
	/**
	 * ��Ŀ���
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "��Ŀ���";
	/**
	 * �������
	 */
	private String prog_id;
	
	public static final String PROG_ID = "�������";
	/**
	 * (����)Ŀ��汾��
	 */
	private String target_ver_num;
	
	public static final String TARGET_VER_NUMCN = "(����)Ŀ��汾��";

	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";

	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return task_bk_desc ��������
	 */
	public String getTask_bk_desc() {
		return this.task_bk_desc;
	}

	/**
	 * @param task_bk_desc ��������
	 */
	public void setTask_bk_desc(String task_bk_desc) {
		this.task_bk_desc = task_bk_desc;
	}

	/**
	 * @return project_name  ��Ŀ���
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 * @param project_name  ��Ŀ���
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 * @return prog_id (����)Ŀ��汾��
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 * @param prog_id (����)Ŀ��汾��
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return target_ver_num (����)Ŀ��汾��
	 */
	public String getTarget_ver_num() {
		return this.target_ver_num;
	}

	/**
	 * @param target_ver_num (����)Ŀ��汾��
	 */
	public void setTarget_ver_num(String target_ver_num) {
		this.target_ver_num = target_ver_num;
	}

	/**
	 * @return work_id ������
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/** 
	 * @param work_id ������
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	
	
	
	

}