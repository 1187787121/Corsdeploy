/**
 * Title: FieldTypeException.java
 * File Description: �ļ�[{FILE}]��[{SHEET}]���ֶ����[{TYPE}]ö�ٲ�֧��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160615
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �ļ�[{FILE}]��[{SHEET}]���ֶ����[{TYPE}]ö�ٲ�֧��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class FieldTypeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_FIELD_TYPE";

	public FieldTypeException() {
		super(ERROR_CODE);
	}
}

