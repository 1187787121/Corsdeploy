/**
 * Title: AddUserTermInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月14日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 新增用户及接入终端输入接口
 * @author HT
 */
public class AddUserTermInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 8636918833239315354L;

	/**
	 * 用户名
	 */
	private String user_id;
	
	public final static String USER_IDCN="用户名";
	
	/**
	 * 终端号
	 */
	private String term_no;
	
	public final static String TERM_NOCN="终端号";
	
	/**
	 * 接入渠道
	 */
	private String channel_code;
	
	public final static String CHANNEL_CODECN="接入渠道";

	/**
	 * @return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return term_no 终端号
	 */
	public String getTerm_no() {
		return this.term_no;
	}

	/**
	 * @param term_no 终端号
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	/**
	 * @return channel_code 接入渠道
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 * @param channel_code 接入渠道
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
}
