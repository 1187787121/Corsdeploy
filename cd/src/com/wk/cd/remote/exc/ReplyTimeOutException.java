/**
 * Title: ReplyTimeOutException.java
 * File Description: 获取返回信息超时
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160715
 */
package com.wk.cd.remote.exc;

import com.wk.lang.*;

/**
 * Class Description: 获取返回信息超时
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ReplyTimeOutException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_REPLY_TIME_OUT";

	public ReplyTimeOutException() {
		super(ERROR_CODE);
	}
}

