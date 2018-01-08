/**
 * Title: PropertiesToBeanErrorException.java
 * File Description: 接口Bean转换错误{CLASS}
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: 接口Bean转换错误{CLASS}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PropertiesToBeanErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_PROPERTIES_TO_BEAN_ERROR";

	public PropertiesToBeanErrorException() {
		super(ERROR_CODE);
	}
}

