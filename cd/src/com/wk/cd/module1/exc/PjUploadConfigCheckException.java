/**
 * Title: PjUploadConfigCheckException.java
 * File Description: 上传到节点[{NODE}]配置文件[{FILE}]失败
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160626
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 上传到节点[{NODE}]配置文件[{FILE}]失败
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PjUploadConfigCheckException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_PJ_UPLOAD_CONFIG_CHECK";

	public PjUploadConfigCheckException() {
		super(ERROR_CODE);
	}
}

