/**
 * Title: QueryChannelOutputBean.java
 * File Description: 查询渠道信息输出接口 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.ch.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.CHANNEL_TYPE;

/**
 * Class Description:查询渠道信息输出接口 
 * @author HT
 */
public class QueryChannelOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 1278298254195787030L;
	
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
}
