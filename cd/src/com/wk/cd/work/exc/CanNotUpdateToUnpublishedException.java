/**
 * Title: CanNotUpdateToUnpublishedException.java
 * File Description: 已发布任务不能修改为未发布
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141211
 */
package com.wk.cd.work.exc;

import com.wk.lang.*;

/**
 * Class Description: 已发布任务不能修改为未发布
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CanNotUpdateToUnpublishedException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORK_CAN_NOT_UPDATE_TO_UNPUBLISHED";

	public CanNotUpdateToUnpublishedException() {
		super(ERROR_CODE);
	}
}

