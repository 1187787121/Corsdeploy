/**
 * Title: GetSocCurrentWorkingException.java
 * File Description: 获取节点[{SOC}]当前用户工作目录失败
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160626
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 获取节点[{SOC}]当前用户工作目录失败
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GetSocCurrentWorkingException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GET_SOC_CURRENT_WORKING";

	public GetSocCurrentWorkingException() {
		super(ERROR_CODE);
	}
}

