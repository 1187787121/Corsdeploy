/**
 * Title: ApproveDeptRoleLackForSrvException.java
 * File Description: ȱ����������{OPT}���Ž�ɫ��Ϣ
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141210
 */
package com.wk.cd.work.exc;

import com.wk.lang.*;

/**
 * Class Description: ȱ����������{OPT}���Ž�ɫ��Ϣ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ApproveDeptRoleLackForSrvException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORK_APPROVE_DEPT_ROLE_LACK_FOR_SRV";

	public ApproveDeptRoleLackForSrvException() {
		super(ERROR_CODE);
	}
}

