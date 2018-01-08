/**
 * Title: RC_FLAG.java
 * File Description: 接收状态
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-3-31
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 接收状态
 * @author AutoGen
 */
public class RC_FLAG extends EnumValue<RC_FLAG> {
	 /** 
	 * 
	 */ 
	private static final long serialVersionUID = 5143472928033181078L;

	private RC_FLAG(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 接收
	  */
	 public static final RC_FLAG YES = new RC_FLAG(1, "接收");

	 /**
	  * 未接收
	  */
	 public static final RC_FLAG NO = new RC_FLAG(2, "未接收");

}
