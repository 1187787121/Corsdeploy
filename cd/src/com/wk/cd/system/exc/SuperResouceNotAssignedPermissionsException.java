/**
 * Title: SuperResouceNotAssignedPermissionsException.java
 * File Description: 上级资源未分配权限
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20150209
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 上级资源{SP_RS_CODE}未分配权限{RS_CODE}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SuperResouceNotAssignedPermissionsException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_SUPER_RESOUCE_NOT_ASSIGNED_PERMISSIONS";

	public SuperResouceNotAssignedPermissionsException() {
		super(ERROR_CODE);
	}
}

