/**
 * Title: CommissionDateLessThanCurrentException.java
 * File Description: 投产日期不能小于当前日期
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151014
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 投产日期不能小于当前日期
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CommissionDateLessThanCurrentException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_COMMISSION_DATE_LESS_THAN_CURRENT";

	public CommissionDateLessThanCurrentException() {
		super(ERROR_CODE);
	}
}

