/**
 * Title: FIELD_TYPE.java
 * File Description: �ֶ�����
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-5-30
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �ֶ�����
 * @author AutoGen
 */
public class FIELD_TYPE extends EnumValue<FIELD_TYPE> {
	
	private static final long serialVersionUID = -6156087211655604184L;

	private FIELD_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * һ�����
	  */
	 public static final FIELD_TYPE GENERAL = new FIELD_TYPE(1, "һ�����");

	 /**
	  * Ͷ����
	  */
	 public static final FIELD_TYPE DPP = new FIELD_TYPE(2, "Ͷ����");
	 
	 /**
	  * �����ļ�
	  */
	 public static final FIELD_TYPE CHECK = new FIELD_TYPE(3, "У���ļ�");

}
