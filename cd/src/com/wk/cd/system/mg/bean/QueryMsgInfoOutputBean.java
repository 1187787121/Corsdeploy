/**
 * Title: QueryMsgInfoOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��4��9��
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ��ѯ��Ϣ��ϸ��Ϣ����ӿ�
 * @author HT
 */
public class QueryMsgInfoOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 6268485361846663862L;
	
	/**
	 *  ��Ϣ��ϸ��Ϣ
	 */
	private MgMsgRcBean msg_info;
	
	public static final String MSG_INFOCN="��Ϣ��ϸ��Ϣ";

	/**
	 * @return msg_info  ��Ϣ��ϸ��Ϣ
	 */
	public MgMsgRcBean getMsg_info() {
		return this.msg_info;
	}

	/**
	 * @param msg_info  ��Ϣ��ϸ��Ϣ
	 */
	public void setMsg_info(MgMsgRcBean msg_info) {
		this.msg_info = msg_info;
	}

}
