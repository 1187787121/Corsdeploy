/**
 * Title: SEQ_TYPE.java
 * File Description: �������
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �������
 * @author AutoGen
 */
public class SEQ_TYPE
		extends EnumValue<SEQ_TYPE> {

	private static final long serialVersionUID = 1059849926731012211L;

	/**
	 * ���캯��
	 */
	private SEQ_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "�������";

	/**
	 * 1 �������
	 */
	public static final SEQ_TYPE PERMANENT = new SEQ_TYPE(1, "�������");

	/**
	 * 2 �����
	 */
	public static final SEQ_TYPE DAILY = new SEQ_TYPE(2, "�����");
}
