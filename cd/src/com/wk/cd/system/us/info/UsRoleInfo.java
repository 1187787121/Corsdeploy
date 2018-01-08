/**
 * Title: UsRoleInfo.java
 * File Description: ��ɫ�ű�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:��ɫ�ű�
 * @author AutoGen
 */
@Table("US_ROLE")
@PrimaryKey({"role_code"})
public class UsRoleInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "��ɫ�ű�";

	/**
	 *��ɫ����
	 */
	private String role_code;

	public static final String ROLE_CODECN = "��ɫ����";

	/**
	 *��ɫ����
	 */
	private String role_cn_name;

	public static final String ROLE_CN_NAMECN = "��ɫ����";

	/**
	 *��ɫ����
	 */
	private int role_type;

	public static final String ROLE_TYPECN = "��ɫ����";

	/**
	 *��ɫ˵��
	 */
	private String role_bk_desc;

	public static final String ROLE_BK_DESCCN = "��ɫ˵��";

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
	 *@return role_code ��ɫ����
	 */
	public String getRole_code() {
		return this.role_code;
	}

	/**
	 *@param role_code ��ɫ����
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	/**
	 *@return role_cn_name ��ɫ����
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 *@param role_cn_name ��ɫ����
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
	}

	/**
	 *@return role_type ��ɫ����
	 */
	public int getRole_type() {
		return this.role_type;
	}

	/**
	 *@param role_type ��ɫ����
	 */
	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}

	/**
	 *@return role_bk_desc ��ɫ˵��
	 */
	public String getRole_bk_desc() {
		return this.role_bk_desc;
	}

	/**
	 *@param role_bk_desc ��ɫ˵��
	 */
	public void setRole_bk_desc(String role_bk_desc) {
		this.role_bk_desc = role_bk_desc;
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
