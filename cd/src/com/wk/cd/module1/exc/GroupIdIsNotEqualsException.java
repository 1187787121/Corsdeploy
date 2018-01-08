/**
 * Title: GroupIdIsNotEqualsException.java
 * File Description: 组件组中ID和文件中ID不相等
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161026
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 组件组中ID和文件中ID不相等
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GroupIdIsNotEqualsException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GROUP_ID_IS_NOT_EQUALS";

	public GroupIdIsNotEqualsException() {
		super(ERROR_CODE);
	}
}

