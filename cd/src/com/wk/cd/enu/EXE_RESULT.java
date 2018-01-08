/**
 * Title: EXE_RESULT.java
 * File Description: Ö´ÐÐ×´Ì¬
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: Ö´ÐÐ×´Ì¬
 * @author AutoGen
 */
public class EXE_RESULT extends EnumValue<EXE_RESULT> {

	private static final long serialVersionUID = -1361386087324805712L;

	private EXE_RESULT(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ³É¹¦
	  */
	 public static final EXE_RESULT SUCCESS = new EXE_RESULT(1, "³É¹¦");

	 /**
	  * Ê§°Ü
	  */
	 public static final EXE_RESULT FAIL = new EXE_RESULT(2, "Ê§°Ü");

}
