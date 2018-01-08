/**
 * Title: ChChannelInfo.java
 * File Description: 渠道定义表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */

package com.wk.cd.system.ch.info;

import java.io.Serializable;

import com.wk.cd.enu.CHANNEL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:渠道定义表
 * @author AutoGen
 */
@Table("CH_CHANNEL")
@PrimaryKey({"channel_code"})
public class ChChannelInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "渠道定义表";

	/**
	 *渠道编码
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "渠道编码";

	/**
	 *渠道中文名称
	 */
	private String channel_cn_name;

	public static final String CHANNEL_CN_NAMECN = "渠道中文名称";

	/**
	 *渠道类型
	 */
	private CHANNEL_TYPE channel_type;

	public static final String CHANNEL_TYPECN = "渠道类型";

	/**
	 *渠道描述
	 */
	private String channel_bk_desc;

	public static final String CHANNEL_BK_DESCCN = "渠道描述";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *记录状态
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "记录状态";

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
	 *@return channel_cn_name 渠道中文名称
	 */
	public String getChannel_cn_name() {
		return this.channel_cn_name;
	}

	/**
	 *@param channel_cn_name 渠道中文名称
	 */
	public void setChannel_cn_name(String channel_cn_name) {
		this.channel_cn_name = channel_cn_name;
	}

	/**
	 *@return channel_type 渠道类型
	 */
	public CHANNEL_TYPE getChannel_type() {
		return this.channel_type;
	}

	/**
	 *@param channel_type 渠道类型
	 */
	public void setChannel_type(CHANNEL_TYPE channel_type) {
		this.channel_type = channel_type;
	}

	/**
	 *@return channel_bk_desc 渠道描述
	 */
	public String getChannel_bk_desc() {
		return this.channel_bk_desc;
	}

	/**
	 *@param channel_bk_desc 渠道描述
	 */
	public void setChannel_bk_desc(String channel_bk_desc) {
		this.channel_bk_desc = channel_bk_desc;
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

	/**
	 *@return rcd_state 记录状态
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state 记录状态
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

}
