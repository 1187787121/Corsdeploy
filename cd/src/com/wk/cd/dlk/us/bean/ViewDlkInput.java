/**
 * Title: QueryDlkLoginInput.java
 * File Description: 查询递蓝科输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年10月25日
 */
package com.wk.cd.dlk.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 查询递蓝科输入接口
 * @author HT
 */
public class ViewDlkInput extends ActionRootInputBean{
	private static final long serialVersionUID = 7602018510563106342L;
	
	/**
	 * 用户名
	 */
	private String userid;

	public static final String USERIDCN = "用户名";
	
	/**
	 * 客户端IP
	 */
	private String remote_ip;
	
	public static final String REMOTE_IPCN1 = "客户端IP";

	/**
	 * @return userid 用户名
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid 用户名
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return remote_ip 客户端IP
	 */
	public String getRemote_ip() {
		return remote_ip;
	}

	/**
	 * @param remote_ip 客户端IP
	 */
	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}
}
