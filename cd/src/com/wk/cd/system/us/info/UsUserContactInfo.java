/**
 * Title: UsUserContactInfo.java
 * File Description: 用户常用联系人关联表
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
 * Class description:用户常用联系人关联表
 * @author AutoGen
 */
@Table("US_USER_CONTACT")
@PrimaryKey({"user_id","contact_user_id"})
public class UsUserContactInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户常用联系人关联表";

	/**
	 *用户ID
	 */
	private String user_id;

	public static final String USER_IDCN = "用户ID";

	/**
	 *用户常用联系人
	 */
	private String contact_user_id;

	public static final String CONTACT_USER_IDCN = "用户常用联系人";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return user_id 用户ID
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id 用户ID
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return contact_user_id 用户常用联系人
	 */
	public String getContact_user_id() {
		return this.contact_user_id;
	}

	/**
	 *@param contact_user_id 用户常用联系人
	 */
	public void setContact_user_id(String contact_user_id) {
		this.contact_user_id = contact_user_id;
	}

	/**
	 *@return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
