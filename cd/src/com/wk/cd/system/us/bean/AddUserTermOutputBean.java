/**
 * Title: AddUserTermOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月14日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 新增用户接入终端输出接口
 * @author HT
 */
public class AddUserTermOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -8625133504187931695L;
	
	/**
	 * 是否启用
	 */
	private boolean is_used;
	
	public static final String IS_USEDCN="是否启用";

	/**
	 * @return is_used 是否启用
	 */
	public boolean isIs_used() {
		return this.is_used;
	}

	/**
	 * @param is_used 是否启用
	 */
	public void setIs_used(boolean is_used) {
		this.is_used = is_used;
	}
}
