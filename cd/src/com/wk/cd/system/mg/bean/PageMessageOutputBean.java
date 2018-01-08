/**
 * Title: PageMessageOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月7日
 */
package com.wk.cd.system.mg.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 分页查询消息信息输出接口
 * @author HT
 */
public class PageMessageOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 6139878036474024909L;
	
	/**
	 * 消息列表
	 */
	private List<MgMsgRcBean> msg_list;
	
	public static String MSG_LISTCN="消息列表";

	/**
	 * @return msg_list 消息列表
	 */
	public List<MgMsgRcBean> getMsg_list() {
		return this.msg_list;
	}

	/**
	 * @param msg_list 消息列表
	 */
	public void setMsg_list(List<MgMsgRcBean> msg_list) {
		this.msg_list = msg_list;
	}
}
