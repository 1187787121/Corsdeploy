/**
 * Title: ELE_TYPE.java
 * File Description: ����Ҫ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-31
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ����Ҫ��
 * @author AutoGen
 */
public class ELE_TYPE extends EnumValue<ELE_TYPE> {
	 /** 
	 * @Fields serialVersionUID : -4532357712947425201L
	 */ 
	private static final long serialVersionUID = -4532357712947425201L;

	private ELE_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * Դ��
	  */
	 public static final ELE_TYPE SOURCE = new ELE_TYPE(1, "Դ��");

	 /**
	  * Ŀ��
	  */
	 public static final ELE_TYPE TARGET = new ELE_TYPE(2, "Ŀ��");

	 /**
	  * ����
	  */
	 public static final ELE_TYPE DATA = new ELE_TYPE(3, "����");

}
