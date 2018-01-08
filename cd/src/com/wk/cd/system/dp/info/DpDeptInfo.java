/**
 * Title: DpDeptInfo.java
 * File Description: ���ű�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.dp.info;

import java.io.Serializable;

import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.*;
/**
 * Class description:���ű�
 * @author AutoGen
 */
@Table("DP_DEPT")
@PrimaryKey({"dept_id"})

public class DpDeptInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���ű�";

	/**
	 *���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";

	/**
	 *��������
	 */
	private String dept_cn_name;

	public static final String DEPT_CN_NAMECN = "��������";
	
	/**
	 * ����ȫ��
	 */
	private String dept_full_cname;
	
	public static final String DEPT_FULL_CNAMECN="����ȫ��";

	/**
	 *��������
	 */
	private DEPT_TYPE dept_type;

	public static final String DEPT_TYPECN = "��������";

	/**
	 *���ż���
	 */
	private int dept_level;

	public static final String DEPT_LEVELCN = "���ż���";

	/**
	 *�ϼ����ű���
	 */
	private String super_dept_id;

	public static final String SUPER_DEPT_IDCN = "�ϼ����ű���";

	/**
	 *
	 */
	private String branch_no;

	public static final String BRANCH_NOCN = "";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *��������
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "��������";

	/**
	 *����ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "����ʱ��";

	/**
	 *�����û�
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "�����û�";

	/**
	 *�޸�����
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "�޸�����";

	/**
	 *�޸�ʱ��
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "�޸�ʱ��";

	/**
	 *�޸��û�
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "�޸��û�";

	/**
	 *ɾ������
	 */
	private JaDate del_bk_date;

	public static final String DEL_BK_DATECN = "ɾ������";

	/**
	 *ɾ��ʱ��
	 */
	private JaTime del_bk_time;

	public static final String DEL_BK_TIMECN = "ɾ��ʱ��";

	/**
	 *ɾ���û�
	 */
	private String del_user_id;

	public static final String DEL_USER_IDCN = "ɾ���û�";

	/**
	 *��¼״̬
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "��¼״̬";

	/**
	 *@return dept_id ���ű���
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 *@param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 *@return dept_cn_name ��������
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 *@param dept_cn_name ��������
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
	 *@return dept_type ��������
	 */
	public DEPT_TYPE getDept_type() {
		return this.dept_type;
	}

	/**
	 *@param dept_type ��������
	 */
	public void setDept_type(DEPT_TYPE dept_type) {
		this.dept_type = dept_type;
	}

	/**
	 *@return dept_level ���ż���
	 */
	public int getDept_level() {
		return this.dept_level;
	}

	/**
	 *@param dept_level ���ż���
	 */
	public void setDept_level(int dept_level) {
		this.dept_level = dept_level;
	}

	/**
	 *@return super_dept_id �ϼ����ű���
	 */
	public String getSuper_dept_id() {
		return this.super_dept_id;
	}

	/**
	 *@param super_dept_id �ϼ����ű���
	 */
	public void setSuper_dept_id(String super_dept_id) {
		this.super_dept_id = super_dept_id;
	}

	/**
	 *@return branch_no 
	 */
	public String getBranch_no() {
		return this.branch_no;
	}

	/**
	 *@param branch_no 
	 */
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}

	/**
	 *@return backup_fld �����ֶ�
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld �����ֶ�
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

	/**
	 *@return crt_bk_date ��������
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date ��������
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time ����ʱ��
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time ����ʱ��
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return crt_user_id �����û�
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id �����û�
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return modify_bk_date �޸�����
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date �޸�����
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time �޸�ʱ��
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time �޸�ʱ��
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return modify_user_id �޸��û�
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id �޸��û�
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	/**
	 *@return del_bk_date ɾ������
	 */
	public JaDate getDel_bk_date() {
		return this.del_bk_date;
	}

	/**
	 *@param del_bk_date ɾ������
	 */
	public void setDel_bk_date(JaDate del_bk_date) {
		this.del_bk_date = del_bk_date;
	}

	/**
	 *@return del_bk_time ɾ��ʱ��
	 */
	public JaTime getDel_bk_time() {
		return this.del_bk_time;
	}

	/**
	 *@param del_bk_time ɾ��ʱ��
	 */
	public void setDel_bk_time(JaTime del_bk_time) {
		this.del_bk_time = del_bk_time;
	}

	/**
	 *@return del_user_id ɾ���û�
	 */
	public String getDel_user_id() {
		return this.del_user_id;
	}

	/**
	 *@param del_user_id ɾ���û�
	 */
	public void setDel_user_id(String del_user_id) {
		this.del_user_id = del_user_id;
	}

	/**
	 *@return rcd_state ��¼״̬
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state ��¼״̬
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

}
