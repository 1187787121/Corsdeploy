/**
 * Title: PasswordOutOfDateException.java
 * File Description: �����ѹ��ڣ�����ϵ����Ա
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151112
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: �����ѹ��ڣ�����ϵ����Ա
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PasswordOutOfDateException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_PASSWORD_OUT_OF_DATE";

	public PasswordOutOfDateException() {
		super(ERROR_CODE);
	}
}

