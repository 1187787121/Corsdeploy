/**
 * Title: OPEN_TYPE.java
 * File Description: ��������
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��������
 * @author AutoGen
 */
public class OPEN_TYPE extends EnumValue<OPEN_TYPE> {
	private static final long serialVersionUID = -5122143827087337022L;
	
	/**
	 * ���캯��
	 */
	private OPEN_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ����
	  */
	 public static final OPEN_TYPE ALLOW = new OPEN_TYPE(1, "����");

	 /**
	  * ��ֹ
	  */
	 public static final OPEN_TYPE FORBID = new OPEN_TYPE(2, "��ֹ");

}
