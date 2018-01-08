/**
 * Title: SCRIPT_METHOD.java
 * File Description: �ű���ʽ
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �ű���ʽ
 * @author AutoGen
 */
public class SCRIPT_METHOD extends EnumValue<SCRIPT_METHOD> {
	
	private static final long serialVersionUID = 6625788646777128699L;

	private SCRIPT_METHOD(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ���
	  */
	 public static final SCRIPT_METHOD COMP = new SCRIPT_METHOD(1, "���");

	 /**
	  * shell�ű�
	  */
	 public static final SCRIPT_METHOD SHELL = new SCRIPT_METHOD(2, "shell�ű�");

	 /**
	  * sql�ű�
	  */
	 public static final SCRIPT_METHOD SQL = new SCRIPT_METHOD(3, "sql�ű�");

}
