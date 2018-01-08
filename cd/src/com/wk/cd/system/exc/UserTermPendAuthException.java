/**
 * Title: UserTermPendAuthException.java
 * File Description: 用户接入终端申请已提交，请等待管理员确认！
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141208
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 用户接入终端申请已提交，请等待管理员确认！
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class UserTermPendAuthException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_USER_TERM_PEND_AUTH";

	public UserTermPendAuthException() {
		super(ERROR_CODE);
	}
}

