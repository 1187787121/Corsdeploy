/**
 * Title: AddInteTasknputBean.java
 * File Description: ���漯����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ���漯����������ӿ�
 * @author Xul
 */
public class AddInteTasknputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 7987545016878658312L;
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
	/**
	 *��������
	 */
	private String task_bk_desc;

	public static final String TASK_BK_DESCCN = "��������";
	
	/**
	 * ������Ŀ
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "������Ŀ";

	/**
	 * �������
	 */
	private String prog_id;

	public static final String PROG_IDCN = "�������";
	
	/**
	 * ����Դ��汾��
	 */
	private String code_ver_num;

	public static final String CODE_VER_NUMCN = "����Դ��汾��";

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
	 *@return task_bk_desc ��������
	 */
	public String getTask_bk_desc() {
		return this.task_bk_desc;
	}

	/**
	 *@param task_bk_desc ��������
	 */
	public void setTask_bk_desc(String task_bk_desc) {
		this.task_bk_desc = task_bk_desc;
	}
	
	/**
	 *@return project_name ������Ŀ
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name ������Ŀ
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 *@return prog_id �������
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 *@param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
	
	/**
	 *@return code_ver_num ����Դ��汾��
	 */
	public String getCode_ver_num() {
		return this.code_ver_num;
	}

	/**
	 *@param code_ver_num ����Դ��汾��
	 */
	public void setCode_ver_num(String code_ver_num) {
		this.code_ver_num = code_ver_num;
	}
}
