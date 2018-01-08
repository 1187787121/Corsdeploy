/**
 * Title: MODIFY_FLAG.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-5-25
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 
 * @author AutoGen
 */
public class SENSITIVE_FLAG extends EnumValue<SENSITIVE_FLAG> {
	
	private static final long serialVersionUID = -4604031165883165622L;

	private SENSITIVE_FLAG(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 1 ÊÇ
	  */
	 public static final SENSITIVE_FLAG YES = new SENSITIVE_FLAG(1, "ÊÇ");

	 /**
	  * 2 ·ñ
	  */
	 public static final SENSITIVE_FLAG NO = new SENSITIVE_FLAG(2, "·ñ");

}
