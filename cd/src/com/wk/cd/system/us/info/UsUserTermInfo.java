/**
 * Title: UsUserTermInfo.java
 * File Description: 用户接入终端配置表
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
 * Class description:用户接入终端配置表
 * @author AutoGen
 */
@Table("US_USER_TERM")
@PrimaryKey({"user_id","term_no"})
public class UsUserTermInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户接入终端配置表";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *终端号
	 */
	private String term_no;

	public static final String TERM_NOCN = "终端号";

	/**
	 *接入渠道
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "接入渠道";
	
	/**
	 *部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";
	
	/**
	 *用户姓名
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "用户姓名";
	
	/**
	 *部门名称
	 */
	private String dept_cn_name;

	public static final String DEPT_CN_NAMECN = "部门名称";
	
	/**
	 * 启用状态
	 */
	private USE_STATE use_state;

	public static final String USE_STATECN = "启用状态";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *@return term_no 终端号
	 */
	public String getTerm_no() {
		return this.term_no;
	}

	/**
	 *@param term_no 终端号
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	/**
	 *@return channel_code 接入渠道
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 *@param channel_code 接入渠道
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	/**
	 * @return dept_id 部门编码
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @return user_cn_name 用户姓名
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name 用户姓名
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return dept_cn_name 部门名称 
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 * @param dept_cn_name 部门名称
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}

	/**
	 * @return use_state 启用状态
	 */
	public USE_STATE getUse_state() {
		return this.use_state;
	}

	/**
	 * @param use_state 启用状态
	 */
	public void setUse_state(USE_STATE use_state) {
		this.use_state = use_state;
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
