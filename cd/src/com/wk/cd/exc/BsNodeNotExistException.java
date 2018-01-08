/**
 * Title: BsNodeNotExistException.java
 * File Description: 当前系统下不存在IP为[{IP}]的节点
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160626
 */
package com.wk.cd.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 不存在IP为[{IP}]的节点
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class BsNodeNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_BS_NODE_NOT_EXIST";

	public BsNodeNotExistException() {
		super(ERROR_CODE);
	}
}

