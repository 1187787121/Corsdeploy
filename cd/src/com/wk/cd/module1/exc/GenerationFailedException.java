/**
 * Title: GenerationConditionExecuteException.java
 * File Description: 生成条件[{CONDITION}]不能运行
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160720
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 实例生成失败因为[{REASON}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GenerationFailedException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_MODULE_GENERATION_FAILED";

	public GenerationFailedException() {
		super(ERROR_CODE);
	}
}

