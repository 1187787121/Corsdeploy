/**
 * Title: WorkStateAbnormalException.java
 * File Description: 任务非{PARM1}状态
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141208
 */
package com.wk.cd.work.exc;

import com.wk.lang.*;

/**
 * Class Description: 任务非{PARM1}状态
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class WorkStateAbnormalException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORK_WORK_STATE_ABNORMAL";

	public WorkStateAbnormalException() {
		super(ERROR_CODE);
	}
}

