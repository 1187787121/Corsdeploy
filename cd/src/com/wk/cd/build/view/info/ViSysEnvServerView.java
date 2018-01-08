/**
 * Title: ViSysEnvServerInfo.java
 * File Description: VIEW
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */

package com.wk.cd.build.view.info;

import java.io.Serializable;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.SERVER_OS;
import com.wk.cd.enu.SYS_TYPE;
/**
 * Class description:系统环境服务器视图
 * @author AutoGen
 */
public class ViSysEnvServerView implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String VIEWCN = "系统环境服务器视图";

	/**
	 *应用系统名称
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统名称";

	/**
	 *应用系统简称
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "应用系统简称";

	/**
	 *应用系统描述
	 */
	private String sys_bk_desc;

	public static final String SYS_BK_DESCCN = "应用系统描述";
	
	/**
	 *应用系统类型
	 */
	private SYS_TYPE sys_type;

	public static final String SYS_TYPECN = "应用系统类型";

	/**
	 *环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";

	/**
	 *环境简称
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "环境简称";

	/**
	 *环境描述
	 */
	private String env_bk_desc;

	public static final String ENV_BK_DESCCN = "环境描述";

	/**
	 *环境类型
	 */
	private ENV_TYPE env_type;

	public static final String ENV_TYPECN = "环境类型";

	/**
	 *数据范围
	 */
	private DT_RANGE dt_range;

	public static final String DT_RANGECN = "数据范围";

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
	 *服务器类型
	 */
	private String server_type;

	public static final String SERVER_TYPECN = "服务器类型";

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
	 *数据库版本号
	 */
	private String db_bbk_ver;

	public static final String DB_BBK_VERCN = "数据库版本号";

	/**
	 *中间件
	 */
	private String server_mid_ware;

	public static final String SERVER_MID_WARECN = "中间件";

	/**
	 *@return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return sys_cn_name 应用系统简称
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 *@param sys_cn_name 应用系统简称
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 *@return sys_bk_desc 应用系统描述
	 */
	public String getSys_bk_desc() {
		return this.sys_bk_desc;
	}

	/**
	 *@param sys_bk_desc 应用系统描述
	 */
	public void setSys_bk_desc(String sys_bk_desc) {
		this.sys_bk_desc = sys_bk_desc;
	}
	
	/**
	 * @return sys_type 应用系统类型
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type 应用系统类型
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 *@return env_name 环境名称
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return env_cn_name 环境简称
	 */
	public String getEnv_cn_name() {
		return this.env_cn_name;
	}

	/**
	 *@param env_cn_name 环境简称
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 *@return env_bk_desc 环境描述
	 */
	public String getEnv_bk_desc() {
		return this.env_bk_desc;
	}

	/**
	 *@param env_bk_desc 环境描述
	 */
	public void setEnv_bk_desc(String env_bk_desc) {
		this.env_bk_desc = env_bk_desc;
	}

	/**
	 *@return env_type 环境类型
	 */
	public ENV_TYPE getEnv_type() {
		return this.env_type;
	}

	/**
	 *@param env_type 环境类型
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 *@return dt_range 数据范围
	 */
	public DT_RANGE getDt_range() {
		return this.dt_range;
	}

	/**
	 *@param dt_range 数据范围
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

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
	 *@return server_bk_desc 服务器描述
	 */
	public String getServer_desc() {
		return this.server_desc;
	}

	/**
	 *@param server_bk_desc 服务器描述
	 */
	public void setServer_desc(String server_desc) {
		this.server_desc = server_desc;
	}

	/**
	 *@return server_type 服务器类型
	 */
	public String getServer_type() {
		return this.server_type;
	}

	/**
	 *@param server_type 服务器类型
	 */
	public void setServer_type(String server_type) {
		this.server_type = server_type;
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
	 *@return db_bbk_ver 数据库版本号
	 */
	public String getDb_bbk_ver() {
		return this.db_bbk_ver;
	}

	/**
	 *@param db_bbk_ver 数据库版本号
	 */
	public void setDb_bbk_ver(String db_bbk_ver) {
		this.db_bbk_ver = db_bbk_ver;
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

}
