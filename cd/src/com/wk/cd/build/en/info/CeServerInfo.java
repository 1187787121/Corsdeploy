/**
 * Title: CeServerInfo.java
 * File Description: 服务器表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-3
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.cd.enu.SERVER_OS;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
/**
 * Class description:服务器表
 * @author AutoGen
 */
@Table("CE_SERVER")
@PrimaryKey({"server_name"})
public class CeServerInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "服务器表";

	/**
	 *服务器名称
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务器名称";

	/**
	 *服务器简称
	 */
	private String server_cn_name;

	public static final String SERVER_CN_NAMECN = "服务器简称";

	/**
	 *服务器描述
	 */
	private String server_desc;

	public static final String SERVER_DESCCN = "服务器描述";

	/**
	 *服务器地址
	 */
	private String server_ip;

	public static final String SERVER_IPCN = "服务器地址";

	/**
	 *操作系统
	 */
	private SERVER_OS server_os;

	public static final String SERVER_OSCN = "操作系统";

	/**
	 *操作系统版本
	 */
	private String os_sbk_ver;

	public static final String OS_SBK_VERCN = "操作系统版本";

	/**
	 *数据库类型
	 */
	private String server_db;

	public static final String SERVER_DBCN = "数据库类型";

	/**
	 *中间件
	 */
	private String server_mid_ware;

	public static final String SERVER_MID_WARECN = "中间件";

	/**
	 *创建日期
	 */
	private JaDate create_bk_date;

	public static final String CREATE_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime create_bk_time;

	public static final String CREATE_BK_TIMECN = "创建时间";

	/**
	 *创建用户
	 */
	private String create_user_id;

	public static final String CREATE_USER_IDCN = "创建用户";

	/**
	 *修改日期
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "修改日期";

	/**
	 *修改时间
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "修改时间";

	/**
	 *修改用户
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "修改用户";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return server_name 服务器名称
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name 服务器名称
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 *@return server_cn_name 服务器简称
	 */
	public String getServer_cn_name() {
		return this.server_cn_name;
	}

	/**
	 *@param server_cn_name 服务器简称
	 */
	public void setServer_cn_name(String server_cn_name) {
		this.server_cn_name = server_cn_name;
	}

	/**
	 *@return server_desc 服务器描述
	 */
	public String getServer_desc() {
		return this.server_desc;
	}

	/**
	 *@param server_desc 服务器描述
	 */
	public void setServer_desc(String server_desc) {
		this.server_desc = server_desc;
	}

	/**
	 *@return server_ip 服务器地址
	 */
	public String getServer_ip() {
		return this.server_ip;
	}

	/**
	 *@param server_ip 服务器地址
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 *@return server_os 操作系统
	 */
	public SERVER_OS getServer_os() {
		return this.server_os;
	}

	/**
	 *@param server_os 操作系统
	 */
	public void setServer_os(SERVER_OS server_os) {
		this.server_os = server_os;
	}

	/**
	 *@return os_sbk_ver 操作系统版本
	 */
	public String getOs_sbk_ver() {
		return this.os_sbk_ver;
	}

	/**
	 *@param os_sbk_ver 操作系统版本
	 */
	public void setOs_sbk_ver(String os_sbk_ver) {
		this.os_sbk_ver = os_sbk_ver;
	}

	/**
	 *@return server_db 数据库类型
	 */
	public String getServer_db() {
		return this.server_db;
	}

	/**
	 *@param server_db 数据库类型
	 */
	public void setServer_db(String server_db) {
		this.server_db = server_db;
	}

	/**
	 *@return server_mid_ware 中间件
	 */
	public String getServer_mid_ware() {
		return this.server_mid_ware;
	}

	/**
	 *@param server_mid_ware 中间件
	 */
	public void setServer_mid_ware(String server_mid_ware) {
		this.server_mid_ware = server_mid_ware;
	}

	/**
	 *@return create_bk_date 创建日期
	 */
	public JaDate getCreate_bk_date() {
		return this.create_bk_date;
	}

	/**
	 *@param create_bk_date 创建日期
	 */
	public void setCreate_bk_date(JaDate create_bk_date) {
		this.create_bk_date = create_bk_date;
	}

	/**
	 *@return create_bk_time 创建时间
	 */
	public JaTime getCreate_bk_time() {
		return this.create_bk_time;
	}

	/**
	 *@param create_bk_time 创建时间
	 */
	public void setCreate_bk_time(JaTime create_bk_time) {
		this.create_bk_time = create_bk_time;
	}

	/**
	 *@return create_user_id 创建用户
	 */
	public String getCreate_user_id() {
		return this.create_user_id;
	}

	/**
	 *@param create_user_id 创建用户
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 *@return modify_bk_date 修改日期
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date 修改日期
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time 修改时间
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time 修改时间
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return modify_user_id 修改用户
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id 修改用户
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
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
