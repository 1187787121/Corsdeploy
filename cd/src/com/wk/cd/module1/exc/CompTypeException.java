/**
 * Title: CompTypeException.java
 * File Description: [{TYPE}]�в�֧�����͵����ã�����������Ϊ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161031
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: [{TYPE}]�в�֧�����͵����ã�����������Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CompTypeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_COMP_TYPE";

	public CompTypeException() {
		super(ERROR_CODE);
	}
}

