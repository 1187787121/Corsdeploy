/**
 * Title: PageChannelOutputBean.java
 * File Description: 分页查询渠道信息输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月10日
 */
package com.wk.cd.system.ch.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.ch.info.ChChannelInfo;

/**
 * Class Description: 分页查询渠道信息输出接口
 * @author HT
 */
public class PageChannelOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = -365054304798560179L;
	
	/**
	 * 渠道信息列表
	 */
	private List<ChChannelInfo> channel_list;
	
	public static final String CHANNEL_LISTCN="渠道信息列表";

	/**
	 * @return channel_list 渠道信息列表
	 */
	public List<ChChannelInfo> getChannel_list() {
		return this.channel_list;
	}

	/**
	 * @param channel_list 渠道信息列表
	 */
	public void setChannel_list(List<ChChannelInfo> channel_list) {
		this.channel_list = channel_list;
	}
}
