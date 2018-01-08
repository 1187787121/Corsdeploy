/**
 * Title: GroupFileIsNotExistException.java
 * File Description: �������ļ�������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161026
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �������ļ�������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GroupFileIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GROUP_FILE_IS_NOT_EXIST";

	public GroupFileIsNotExistException() {
		super(ERROR_CODE);
	}
}

