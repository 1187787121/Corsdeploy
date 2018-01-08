/**
 * Title: UsUserTermInfo.java
 * File Description: �û������ն����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-9-14
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.cd.enu.USE_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:�û������ն����ñ�
 * @author AutoGen
 */
@Table("US_USER_TERM")
@PrimaryKey({"user_id","term_no"})
public class UsUserTermInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�û������ն����ñ�";

	/**
	 *�û���
	 */
	private String user_id;

	public static final String USER_IDCN = "�û���";

	/**
	 *�ն˺�
	 */
	private String term_no;

	public static final String TERM_NOCN = "�ն˺�";

	/**
	 *��������
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "��������";
	
	/**
	 *���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";
	
	/**
	 *�û�����
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "�û�����";
	
	/**
	 *��������
	 */
	private String dept_cn_name;

	public static final String DEPT_CN_NAMECN = "��������";
	
	/**
	 * ����״̬
	 */
	private USE_STATE use_state;

	public static final String USE_STATECN = "����״̬";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return user_id �û���
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return term_no �ն˺�
	 */
	public String getTerm_no() {
		return this.term_no;
	}

	/**
	 *@param term_no �ն˺�
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	/**
	 *@return channel_code ��������
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 *@param channel_code ��������
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

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
	 * @return user_cn_name �û�����
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name �û�����
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return dept_cn_name �������� 
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 * @param dept_cn_name ��������
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}

	/**
	 * @return use_state ����״̬
	 */
	public USE_STATE getUse_state() {
		return this.use_state;
	}

	/**
	 * @param use_state ����״̬
	 */
	public void setUse_state(USE_STATE use_state) {
		this.use_state = use_state;
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

}
