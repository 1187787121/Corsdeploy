/**
 * Title: UsUserContactInfo.java
 * File Description: �û�������ϵ�˹�����
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-11-18
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:�û�������ϵ�˹�����
 * @author AutoGen
 */
@Table("US_USER_CONTACT")
@PrimaryKey({"user_id","contact_user_id"})
public class UsUserContactInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�û�������ϵ�˹�����";

	/**
	 *�û�ID
	 */
	private String user_id;

	public static final String USER_IDCN = "�û�ID";

	/**
	 *�û�������ϵ��
	 */
	private String contact_user_id;

	public static final String CONTACT_USER_IDCN = "�û�������ϵ��";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return user_id �û�ID
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id �û�ID
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return contact_user_id �û�������ϵ��
	 */
	public String getContact_user_id() {
		return this.contact_user_id;
	}

	/**
	 *@param contact_user_id �û�������ϵ��
	 */
	public void setContact_user_id(String contact_user_id) {
		this.contact_user_id = contact_user_id;
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
