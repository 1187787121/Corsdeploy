/**
 * Title: ReadConfigFileException.java
 * File Description: ��ȡ�����ļ�[{FILE}]��������[{CONFIG}]ʧ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160412
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: ��ȡ�����ļ�[{FILE}]��������[{CONFIG}]ʧ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ReadConfigFileException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_READ_CONFIG_FILE";

	public ReadConfigFileException() {
		super(ERROR_CODE);
	}
}

