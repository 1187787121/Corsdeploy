/**
 * Title: ChChannelSrvgPrivInfo.java
 * File Description: 渠道服务组权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */

package com.wk.cd.system.ch.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:渠道服务组权限配置表
 * @author AutoGen
 */
@Table("CH_CHANNEL_SRVG_PRIV")
@PrimaryKey({"channel_code","srvg_code"})
public class ChChannelSrvgPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "渠道服务组权限配置表";

	/**
	 *渠道编码
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "渠道编码";

	/**
	 *服务组编码
	 */
	private String srvg_code;

	public static final String SRVG_CODECN = "服务组编码";

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
	 *@return srvg_code 服务组编码
	 */
	public String getSrvg_code() {
		return this.srvg_code;
	}

	/**
	 *@param srvg_code 服务组编码
	 */
	public void setSrvg_code(String srvg_code) {
		this.srvg_code = srvg_code;
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
