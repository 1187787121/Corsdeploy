/**
 * Title: UsUserOptPrivInfo.java
 * File Description: 用户操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.cd.enu.PRIV_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:用户操作权限配置表
 * @author AutoGen
 */
@Table("US_USER_OPT_PRIV")
@PrimaryKey({"opt_code","user_id"})
public class UsUserOptPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户操作权限配置表";

	/**
	 *操作编码
	 */
	private String opt_code;

	public static final String OPT_CODECN = "操作编码";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *权限
	 */
	private PRIV_FLAG priv_flag;

	public static final String PRIV_FLAGCN = "权限";

	/**
	 *@return opt_code 操作编码
	 */
	public String getOpt_code() {
		return this.opt_code;
	}

	/**
	 *@param opt_code 操作编码
	 */
	public void setOpt_code(String opt_code) {
		this.opt_code = opt_code;
	}

	/**
	 *@return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return priv_flag 权限
	 */
	public PRIV_FLAG getPriv_flag() {
		return this.priv_flag;
	}

	/**
	 *@param priv_flag 权限
	 */
	public void setPriv_flag(PRIV_FLAG priv_flag) {
		this.priv_flag = priv_flag;
	}

}
