/**
 * Title: ENV_TYPE.java
 * File Description: ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��������
 * @author AutoGen
 */
public class ENV_TYPE extends EnumValue<ENV_TYPE> {
	 /**
	 * 3490597017393526572L
	 */
	private static final long serialVersionUID = 3490597017393526572L;

	private ENV_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ��������
	  */
	 public static final ENV_TYPE DEVELOPMENT = new ENV_TYPE(1, "��������");

	 /**
	  * ���Ի���
	  */
	 public static final ENV_TYPE TEST = new ENV_TYPE(2, "���Ի���");

	 /**
	  * �汾����
	  */
	 public static final ENV_TYPE VERSION = new ENV_TYPE(3, "�汾����");

	 /**
	  * ׼��������
	  */
	 public static final ENV_TYPE PRODUCTING = new ENV_TYPE(4, "׼��������");

	 /**
	  * ��������
	  */
	 public static final ENV_TYPE PRODUCT = new ENV_TYPE(5, "��������");

}
