/**
 * Title: STORAGE_RESULT.java
 * File Description: �����
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �����
 * @author AutoGen
 */
public class STORAGE_RESULT extends EnumValue<STORAGE_RESULT> {

	private static final long serialVersionUID = -91310907448954959L;

	private STORAGE_RESULT(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * �ɹ�
	  */
	 public static final STORAGE_RESULT SUCCESS = new STORAGE_RESULT(1, "�ɹ�");

	 /**
	  * ʧ��
	  */
	 public static final STORAGE_RESULT FAIL = new STORAGE_RESULT(2, "ʧ��");

}
