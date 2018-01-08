/**
 * Title: ComponentIsQuotedException.java
 * File Description: {COMPTYPE}ID:[{COMPID}]被引用，不可删除
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161020
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {COMPTYPE}ID:[{COMPID}]被引用，不可删除
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ComponentIsQuotedException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_COMPONENT_IS_QUOTED";

	public ComponentIsQuotedException() {
		super(ERROR_CODE);
	}
}

