/**
 * Title: PEND_TYPE.java
 * File Description: ������ʽ
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-31
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ������ʽ
 * @author AutoGen
 */
public class PEND_TYPE extends EnumValue<PEND_TYPE> {
	
	private static final long serialVersionUID = -4117171289906850717L;

	private PEND_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 1 ����
	  */
	 public static final PEND_TYPE APPLY = new PEND_TYPE(1, "����");

	 /**
	  * 2 ����
	  */
	 public static final PEND_TYPE RECHECK = new PEND_TYPE(2, "����");

	 /**
	  * 3 ��Ȩ
	  */
	 public static final PEND_TYPE AUTH = new PEND_TYPE(3, "��Ȩ");

	 /**
	  * 4 ִ��
	  */
	 public static final PEND_TYPE EXE = new PEND_TYPE(4, "ִ��");
	 
	 /**
	  * 5 �ر�
	  */
	 public static final PEND_TYPE CLOSE = new PEND_TYPE(5, "�ر�");

}
