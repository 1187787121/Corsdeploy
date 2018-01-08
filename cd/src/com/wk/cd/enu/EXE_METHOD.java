/**
 * Title: EXE_METHOD.java
 * File Description: ִ�ж���
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-6
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ִ�ж���
 * @author AutoGen
 */
public class EXE_METHOD extends EnumValue<EXE_METHOD> {

	private static final long serialVersionUID = 8070314212481690140L;

	private EXE_METHOD(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ����ִ��
	  */
	 public static final EXE_METHOD STEP = new EXE_METHOD(1, "����ִ��");

	 /**
	  * һ��ִ��
	  */
	 public static final EXE_METHOD TOEND = new EXE_METHOD(2, "һ��ִ��");

	 /**
	  * һ��ִ������
	  */
	 public static final EXE_METHOD TOREMAIN = new EXE_METHOD(3, "һ��ִ������");

}
