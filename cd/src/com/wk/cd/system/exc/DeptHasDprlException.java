/**
 * Title: DeptHasDprlException.java
 * File Description: ����{PARM1}�д���δɾ��{PARM2}
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20150326
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: ����{PARM1}�д���δɾ��{PARM2}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class DeptHasDprlException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_DEPT_HAS_DPRL";

	public DeptHasDprlException() {
		super(ERROR_CODE);
	}
}

