/**
 * Title: NewInstanceErrorException.java
 * File Description: {CLSNAME}��������ʧ��
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {CLSNAME}��������ʧ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class NewInstanceErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_NEW_INSTANCE_ERROR";

	public NewInstanceErrorException() {
		super(ERROR_CODE);
	}
}

