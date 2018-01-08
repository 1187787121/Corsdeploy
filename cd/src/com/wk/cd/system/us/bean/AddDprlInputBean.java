/**
 * Title: AddDprlInputBean.java
 * File Description: �������Ž�ɫ������Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��6��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �������Ž�ɫ������Ϣ����ӿ�
 * @author HT
 */
public class AddDprlInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 6492326328673215336L;
	
	/**
	 * ���ű���
	 */
	private String dept_id;
	
	public static final String DEPT_IDCN = "���ű���";

	/**
	 * ��ɫ����
	 */
	private String role_code;
	
	public static final String ROLE_CODECN = "��ɫ����";

	/**
	 * ���Ž�ɫ˵��
	 */
	private String bk_expl;
	
	public static final String BK_EXPLCN = "���Ž�ɫ˵��";
	
	/**
	 * @return dept_id ���ű���
	 */
	public String getDept_id() {
		return this.dept_id;
	}
	/**
	 * @param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	/**
	 * @return role_code ��ɫ����
	 */
	public String getRole_code() {
		return this.role_code;
	}
	/**
	 * @param role_code ��ɫ����
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	/** 
	 * @return bk_expl ���Ž�ɫ˵��
	 */
	public String getBk_expl() {
		return this.bk_expl;
	}
	/**
	 * @param bk_expl ���Ž�ɫ˵��
	 */
	public void setBk_expl(String bk_expl) {
		this.bk_expl = bk_expl;
	}
}
