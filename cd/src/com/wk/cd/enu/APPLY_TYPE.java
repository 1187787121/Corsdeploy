/**
 * Title: APPLY_TYPE.java
 * File Description: ����Դ��;
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-3
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ����Դ��;
 * @author AutoGen
 */
public class APPLY_TYPE extends EnumValue<APPLY_TYPE> {
	 /** 
	 * @Fields serialVersionUID : -7668851366002072113L
	 */ 
	private static final long serialVersionUID = -7668851366002072113L;

	private APPLY_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ����
	  */
	 public static final APPLY_TYPE CONFIG = new APPLY_TYPE(2, "��ͨ������");

	 /**
	  * ��ͨ
	  */
	 public static final APPLY_TYPE GENERAL = new APPLY_TYPE(1, "��ͨ");

}
