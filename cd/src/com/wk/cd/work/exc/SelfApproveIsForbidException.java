/**
 * Title: SelfApproveIsForbidException.java
 * File Description: 不允许自我审批{OPT}
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141209
 */
package com.wk.cd.work.exc;

import com.wk.lang.*;

/**
 * Class Description: 不允许自我审批{OPT}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SelfApproveIsForbidException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORK_SELF_APPROVE_IS_FORBID";

	public SelfApproveIsForbidException() {
		super(ERROR_CODE);
	}
}

