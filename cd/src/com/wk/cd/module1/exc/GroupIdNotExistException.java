/**
 * Title: GroupIdNotExistException.java
 * File Description: ������[{ID}]������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160809
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ������[{ID}]������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GroupIdNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GROUP_ID_NOT_EXIST";

	public GroupIdNotExistException() {
		super(ERROR_CODE);
	}
}

