/**
 * Title: NotSupportDiffDeptException.java
 * File Description: �ý��ײ�֧�ֿ粿�Ų���
 * @copyright 2014
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20141126
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: �ý��ײ�֧�ֿ粿�Ų���
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class NotSupportDiffDeptException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_NOT_SUPPORT_DIFF_DEPT";

	public NotSupportDiffDeptException() {
		super(ERROR_CODE);
	}
}

