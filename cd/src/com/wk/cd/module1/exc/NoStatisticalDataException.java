/**
 * Title: NoStatisticalDataException.java
 * File Description: 无{YEAR}年统计数据
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160201
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 无{YEAR}年统计数据
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class NoStatisticalDataException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_NO_STATISTICAL_DATA";

	public NoStatisticalDataException() {
		super(ERROR_CODE);
	}
}

