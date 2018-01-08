/**
 * Title: PageMessageInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��4��7��
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.RC_FLAG;

/**
 * Class Description: ��ҳ��ѯ��Ϣ��Ϣ����ӿ�
 * @author HT
 */
public class PageMessageInputBean extends PageQueryActionRootInputBean{

	private static final long serialVersionUID = -3321432673227389106L;
	
	/**
	 * ��Ϣ����
	 */
	private String msg_title;
	
	public static final String MSG_TITLECN="��Ϣ����";
	
	/**
	 *��Ϣ����
	 */
	private MSG_TYPE msg_type;

	public static final String MSG_TYPECN = "��Ϣ����";
	
	/**
	 *����״̬
	 */
	private RC_FLAG rc_flag;

	public static final String RC_FLAGCN = "����״̬";

	/**
	 * @return msg_title ��Ϣ����
	 */
	public String getMsg_title() {
		return this.msg_title;
	}

	/**
	 * @param msg_title ��Ϣ����
	 */
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	/**
	 * @return msg_type ��Ϣ����
	 */
	public MSG_TYPE getMsg_type() {
		return this.msg_type;
	}

	/**
	 * @param msg_type ��Ϣ����
	 */
	public void setMsg_type(MSG_TYPE msg_type) {
		this.msg_type = msg_type;
	}

	/**
	 * @return rc_flag ����״̬
	 */
	public RC_FLAG getRc_flag() {
		return this.rc_flag;
	}

	/**
	 * @param rc_flag ����״̬
	 */
	public void setRc_flag(RC_FLAG rc_flag) {
		this.rc_flag = rc_flag;
	}
	
}
