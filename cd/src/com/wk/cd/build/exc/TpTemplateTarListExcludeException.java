/**
 * Title: TpTemplateTarListExcludeException.java
 * File Description: 列表中Tar清单不包含{PARM}
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151021
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: 列表中Tar清单不包含{PARM}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpTemplateTarListExcludeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_TEMPLATE_TAR_LIST_EXCLUDE";

	public TpTemplateTarListExcludeException() {
		super(ERROR_CODE);
	}
}

