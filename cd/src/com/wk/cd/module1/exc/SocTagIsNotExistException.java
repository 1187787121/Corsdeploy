/**
 * Title: SocTagIsNotExistExcepionException.java
 * File Description: 节点[{NODE}]中的"数据源名"和"数据源标签"都不能为空
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160223
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 节点[{NODE}]中的"数据源名"和"数据源标签"都不能为空
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SocTagIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOC_TAG_IS_NOT_EXIST_EXCEPION";

	public SocTagIsNotExistException() {
		super(ERROR_CODE);
	}
}

