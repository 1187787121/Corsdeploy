/**
 * Title: ChChannelSrvPrivInfo.java
 * File Description: 渠道服务权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */

package com.wk.cd.system.ch.info;

import java.io.Serializable;

import com.wk.cd.enu.AF_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:渠道服务权限配置表
 * @author AutoGen
 */
@Table("CH_CHANNEL_SRV_PRIV")
@PrimaryKey({"channel_code","srv_name"})
public class ChChannelSrvPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "渠道服务权限配置表";

	/**
	 *渠道编码
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "渠道编码";

	/**
	 *服务名
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名";

	/**
	 *允许禁止标志
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return channel_code 渠道编码
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 *@param channel_code 渠道编码
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	/**
	 *@return srv_name 服务名
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 *@param srv_name 服务名
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 *@return af_flag 允许禁止标志
	 */
	public AF_FLAG getAf_flag() {
		return this.af_flag;
	}

	/**
	 *@param af_flag 允许禁止标志
	 */
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
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
