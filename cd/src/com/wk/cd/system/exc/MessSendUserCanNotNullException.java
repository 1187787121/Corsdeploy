/**
 * Title: MessSendUserCanNotNullException.java
 * File Description: ��Ϣ�����û�����Ϊ��
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20150605
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: ��Ϣ�����û�����Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class MessSendUserCanNotNullException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_MESS_SEND_USER_CAN_NOT_NULL";

	public MessSendUserCanNotNullException() {
		super(ERROR_CODE);
	}
}

