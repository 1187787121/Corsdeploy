/**
 * Title: TemplateFileNameRepeatException.java
 * File Description: ִ��ģ�������ģ���ļ���[{NAME}]�ظ�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160418
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ִ��ģ�������ģ���ļ���[{NAME}]�ظ�
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TemplateFileNameRepeatException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TEMPLATE_FILE_NAME_REPEAT";

	public TemplateFileNameRepeatException() {
		super(ERROR_CODE);
	}
}

