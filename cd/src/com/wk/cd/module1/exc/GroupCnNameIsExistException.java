/**
 * Title: GroupCnNameIsExistException.java
 * File Description: 分组中文名[{NAME}]已经存在，请重新命名分组
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160815
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 分组中文名[{NAME}]已经存在，请重新命名分组
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GroupCnNameIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GROUP_CN_NAME_IS_EXIST";

	public GroupCnNameIsExistException() {
		super(ERROR_CODE);
	}
}

