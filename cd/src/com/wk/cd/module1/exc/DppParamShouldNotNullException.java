/**
 * Title: DppParamShouldNotNullException.java
 * File Description: Ͷ����[{DPP}]�д���Ͷ��������Ϊ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160615
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: Ͷ����[{DPP}]�д���Ͷ��������Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class DppParamShouldNotNullException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_DPP_PARAM_SHOULD_NOT_NULL";

	public DppParamShouldNotNullException() {
		super(ERROR_CODE);
	}
}

