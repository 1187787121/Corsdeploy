/**
 * Title: ListTarNameIsEmptyException.java
 * File Description: �嵥��[{SHEET}]��Ӧ�����߰���Ϊ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160425
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �嵥��[{SHEET}]��Ӧ�����߰���Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ListTarNameIsEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_LIST_TAR_NAME_IS_EMPTY";

	public ListTarNameIsEmptyException() {
		super(ERROR_CODE);
	}
}

