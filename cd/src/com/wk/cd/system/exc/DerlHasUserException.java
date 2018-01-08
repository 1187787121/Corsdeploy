/**
 * Title: DerlHasUserException.java
 * File Description: 部门角色{PARM}存在未删除用户
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20150326
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 部门角色{PARM}存在未删除用户
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class DerlHasUserException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_DERL_HAS_USER";

	public DerlHasUserException() {
		super(ERROR_CODE);
	}
}

