/**
 * Title: NodeIsUsedCanNotModifyException.java
 * File Description: ��Ŀ[{PROJ}]ʹ�øýڵ�,����[{DESC}]
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160128
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��Ŀ[{PROJ}]ʹ�øýڵ�,����[{DESC}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class NodeIsUsedCanNotModifyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_NODE_IS_USED_CAN_NOT_MODIFY";

	public NodeIsUsedCanNotModifyException() {
		super(ERROR_CODE);
	}
}

