/**
 * Title: RC_FLAG.java
 * File Description: ����״̬
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-3-31
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ����״̬
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
	  * ����
	  */
	 public static final RC_FLAG YES = new RC_FLAG(1, "����");

	 /**
	  * δ����
	  */
	 public static final RC_FLAG NO = new RC_FLAG(2, "δ����");

}
