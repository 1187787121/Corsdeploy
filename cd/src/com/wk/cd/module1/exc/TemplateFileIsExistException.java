/**
 * Title: TemplateFileIsExistException.java
 * File Description: [{STRING}]ģ���ļ�[{FILE}]�Ѿ�����
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160108
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: [{STRING}]ģ���ļ�[{FILE}]�Ѿ�����
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TemplateFileIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TEMPLATE_FILE_IS_EXIST";

	public TemplateFileIsExistException() {
		super(ERROR_CODE);
	}
}

