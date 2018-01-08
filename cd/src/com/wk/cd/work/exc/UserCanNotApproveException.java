/**
 * Title: UserCanNotApproveException.java
 * File Description: 用户{USER}不能审批服务{OPT}
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141210
 */
package com.wk.cd.work.exc;

import com.wk.lang.*;

/**
 * Class Description: 用户{USER}不能审批服务{OPT}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class UserCanNotApproveException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORK_USER_CAN_NOT_APPROVE";

	public UserCanNotApproveException() {
		super(ERROR_CODE);
	}
}

