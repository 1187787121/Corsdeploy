/**
 * Title: TpTemplateTarListDifferentException.java
 * File Description: �б���Tar�嵥��ʵ��Tar���嵥��һ��
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151021
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: �б���Tar�嵥��ʵ��Tar���嵥��һ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpTemplateTarListDifferentException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_TEMPLATE_TAR_LIST_DIFFERENT";

	public TpTemplateTarListDifferentException() {
		super(ERROR_CODE);
	}
}
