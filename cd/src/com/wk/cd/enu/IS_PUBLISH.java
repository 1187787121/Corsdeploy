/**
 * Title: IS_PUBLISH.java
 * File Description: �Ƿ񷢲�
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �Ƿ񷢲�
 * @author AutoGen
 */
public class IS_PUBLISH extends EnumValue<IS_PUBLISH> {

	private static final long serialVersionUID = -1304007717963181371L;

	private IS_PUBLISH(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ��
	  */
	 public static final IS_PUBLISH YES = new IS_PUBLISH(1, "��");

	 /**
	  * ��
	  */
	 public static final IS_PUBLISH NO = new IS_PUBLISH(2, "��");

}
