/**
 * Title: USE_STATE.java
 * File Description: ����״̬
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-15
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ����״̬
 * @author AutoGen
 */
public class USE_STATE extends EnumValue<USE_STATE> {

	private static final long serialVersionUID = 6277031403997888836L;

	private USE_STATE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ������
	  */
	 public static final USE_STATE UNUSE = new USE_STATE(1, "������");

	 /**
	  * ������
	  */
	 public static final USE_STATE USED = new USE_STATE(2, "������");

}
