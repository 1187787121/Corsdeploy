/**
 * Title: BsNodeNotExistException.java
 * File Description: ��ǰϵͳ�²�����IPΪ[{IP}]�Ľڵ�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160626
 */
package com.wk.cd.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ������IPΪ[{IP}]�Ľڵ�
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

