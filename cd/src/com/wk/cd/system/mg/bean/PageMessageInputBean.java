/**
 * Title: PageMessageInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月7日
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.RC_FLAG;

/**
 * Class Description: 分页查询消息信息输入接口
 * @author HT
 */
public class PageMessageInputBean extends PageQueryActionRootInputBean{

	private static final long serialVersionUID = -3321432673227389106L;
	
	/**
	 * 消息主题
	 */
	private String msg_title;
	
	public static final String MSG_TITLECN="消息主题";
	
	/**
	 *消息类型
	 */
	private MSG_TYPE msg_type;

	public static final String MSG_TYPECN = "消息类型";
	
	/**
	 *接收状态
	 */
	private RC_FLAG rc_flag;

	public static final String RC_FLAGCN = "接收状态";

	/**
	 * @return msg_title 消息主题
	 */
	public String getMsg_title() {
		return this.msg_title;
	}

	/**
	 * @param msg_title 消息主题
	 */
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	/**
	 * @return msg_type 消息类型
	 */
	public MSG_TYPE getMsg_type() {
		return this.msg_type;
	}

	/**
	 * @param msg_type 消息类型
	 */
	public void setMsg_type(MSG_TYPE msg_type) {
		this.msg_type = msg_type;
	}

	/**
	 * @return rc_flag 接收状态
	 */
	public RC_FLAG getRc_flag() {
		return this.rc_flag;
	}

	/**
	 * @param rc_flag 接收状态
	 */
	public void setRc_flag(RC_FLAG rc_flag) {
		this.rc_flag = rc_flag;
	}
	
}
