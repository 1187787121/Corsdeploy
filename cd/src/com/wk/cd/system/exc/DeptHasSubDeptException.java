/**
 * Title: DeptHasSubDeptException.java
 * File Description: 部门{PARM}有下级部门
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 部门{PARM}有下级部门
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class DeptHasSubDeptException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_DEPT_HAS_SUB_DEPT";

	public DeptHasSubDeptException() {
		super(ERROR_CODE);
	}
}

