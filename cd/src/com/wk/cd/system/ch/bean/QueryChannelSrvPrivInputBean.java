/**
 * Title: QueryChannelSrvPrivInputBean.java
 * File Description: 查询渠道服务权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月13日
 */
package com.wk.cd.system.ch.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 查询渠道服务权限输入接口
 * @author HT
 */
public class QueryChannelSrvPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 976777341076159825L;
	
	/**
	 * 渠道编码
	 */
	private String channel_code;
	
	public static final String CHANNEL_CODECN="渠道编码";

	/**
	 * @return channel_code 渠道编码
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 * @param channel_code 渠道编码
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
}
