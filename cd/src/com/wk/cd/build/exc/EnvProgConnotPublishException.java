/**
 * Title: EnvProgConnotPublishException.java
 * File Description: ��������[{PROGID}]��[{REASON}]�����ܷ���
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170106
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: ��������[{PROGID}]��[{REASON}]�����ܷ���
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvProgConnotPublishException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_PROG_CONNOT_PUBLISH";

	public EnvProgConnotPublishException() {
		super(ERROR_CODE);
	}
}

