/**
 * Title: AddUserTermOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��14��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: �����û������ն�����ӿ�
 * @author HT
 */
public class AddUserTermOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -8625133504187931695L;
	
	/**
	 * �Ƿ�����
	 */
	private boolean is_used;
	
	public static final String IS_USEDCN="�Ƿ�����";

	/**
	 * @return is_used �Ƿ�����
	 */
	public boolean isIs_used() {
		return this.is_used;
	}

	/**
	 * @param is_used �Ƿ�����
	 */
	public void setIs_used(boolean is_used) {
		this.is_used = is_used;
	}
}
