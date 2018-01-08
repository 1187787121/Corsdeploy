/**
 * Title: QueryDeptInfoOutputBean.java
 * File Description: ��ѯ������ϸ��Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��25��
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.DEPT_TYPE;

/**
 * Class Description: ��ѯ������ϸ��Ϣ����ӿ�
 * @author HT
 */
public class QueryDeptInfoOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -3444582666715939647L;
	
	/**
	 * ���ű���
	 */
	private String dept_id;
	
	public static final String DEPT_IDCN = "���ű���";
	
	/**
	 * ���ż��
	 */
	private String dept_cn_name ;
	
	public static final String DEPT_CN_NAMECN = "���ż��";
	
	/**
	 * ����ȫ��
	 */
	private String dept_full_cname ;
	
	public static final String DEPT_FULL_CNAMECN = "����ȫ��";
	
	/**
	 * ��������
	 */
	private DEPT_TYPE dept_type ;
	
	public static final String DEPT_TYPECN = "��������";
	
	/**
	 * ���ż���
	 */
	private int dept_level ;
	
	public static final String DEPT_LEVELCN = "���ż���";
	
	/**
	 * �ϼ����ű���
	 */
	private String super_dept_id ;
	
	public static final String SUPER_DEPT_IDCN = "�ϼ����ű���";
	
	/**
	 * �ϼ���������
	 */
	private String super_dept_cn_name ;
	
	public static final String SUPER_DEPT_CN_NAMECN = "�ϼ���������";
	
	/**
	 * ������
	 */
	private String branch_no;
	
	public static final String BRANCH_NOCN = "������";
	
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
	 * @return dept_cn_name ���ż��
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}
	
	/**
	 * @param dept_cn_name ���ż��
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}
	
	/**
	 * @return dept_full_cname ����ȫ��
	 */
	public String getDept_full_cname() {
		return this.dept_full_cname;
	}
	
	/**
	 * @param dept_full_cname ����ȫ��
	 */
	public void setDept_full_cname(String dept_full_cname) {
		this.dept_full_cname = dept_full_cname;
	}
	
	/**
	 * @return dept_type ��������
	 */
	public DEPT_TYPE getDept_type() {
		return this.dept_type;
	}
	
	/**
	 * @param dept_type ��������
	 */
	public void setDept_type(DEPT_TYPE dept_type) {
		this.dept_type = dept_type;
	}
	
	/**
	 * @return dept_level ���ż���
	 */
	public int getDept_level() {
		return this.dept_level;
	}
	
	/**
	 * @param dept_level ���ż���
	 */
	public void setDept_level(int dept_level) {
		this.dept_level = dept_level;
	}
	
	/**
	 * @return super_dept_id �ϼ����ű���
	 */
	public String getSuper_dept_id() {
		return this.super_dept_id;
	}
	
	/**
	 * @param super_dept_id �ϼ����ű���
	 */
	public void setSuper_dept_id(String super_dept_id) {
		this.super_dept_id = super_dept_id;
	}
	
	/**
	 * @return super_dept_cn_name �ϼ���������
	 */
	public String getSuper_dept_cn_name() {
		return this.super_dept_cn_name;
	}
	
	/**
	 * @param super_dept_cn_name �ϼ���������
	 */
	public void setSuper_dept_cn_name(String super_dept_cn_name) {
		this.super_dept_cn_name = super_dept_cn_name;
	}
	
	/**
	 * @return branch_no ������
	 */
	public String getBranch_no() {
		return this.branch_no;
	}
	
	/**
	 * @param branch_no ������
	 */
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}
}
