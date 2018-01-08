/**
 * Title: NodeSourceTagUnrealizedException.java
 * File Description: 该节点下数据源标签为{TAG}未实现
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151022
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 该节点下数据源标签为{TAG}未实现
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class NodeSourceTagUnrealizedException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_NODE_SOURCE_TAG_UNREALIZED";

	public NodeSourceTagUnrealizedException() {
		super(ERROR_CODE);
	}
}

