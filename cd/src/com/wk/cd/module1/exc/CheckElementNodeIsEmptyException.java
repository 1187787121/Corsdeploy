/**
 * Title: CheckElementNodeIsEmptyException.java
 * File Description: [{ELEMENT}]Ԫ����û��[{TAG}]��Ӧ���ӽڵ�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161025
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: [{ELEMENT}]Ԫ����û��[{TAG}]��Ӧ���ӽڵ�
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CheckElementNodeIsEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_CHECK_ELEMENT_NODE_IS_EMPTY";

	public CheckElementNodeIsEmptyException() {
		super(ERROR_CODE);
	}
}

