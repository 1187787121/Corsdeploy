/**
 * Title: SocTagNeedOnlyInNodeException.java
 * File Description: 节点[{NODE}]下数据源标签[{TAG}]不能对应多个数据源
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160219
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 节点[{NODE}]下数据源标签[{TAG}]不能对应多个数据源
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SocTagNeedOnlyInNodeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOC_TAG_NEED_ONLY_IN_NODE";

	public SocTagNeedOnlyInNodeException() {
		super(ERROR_CODE);
	}
}

