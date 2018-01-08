/**
 * Title: COL_TYPE.java
 * File Description: ���ֶ�����
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-09-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ���ֶ�����
 * @author AutoGen
 */
public class COL_TYPE
		extends EnumValue<COL_TYPE> {

	public static final long serialVersionUID = 204153573290346014L;

	/**
	* ���캯��
	*/
	private COL_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "���ֶ�����";

	/**
	* 1 �ַ���
	*/
	public static final COL_TYPE STRING = new COL_TYPE(1, "�ַ���");

	/**
	* 2 ����
	*/
	public static final COL_TYPE NUMBER = new COL_TYPE(2, "����");

	/**
	* 3 ʱ��
	*/
	public static final COL_TYPE TIME = new COL_TYPE(3, "ʱ��");

	/**
	* 4 ����
	*/
	public static final COL_TYPE DATE = new COL_TYPE(4, "����");

	/**
	* 5 ����ʱ��
	*/
	public static final COL_TYPE DATETIME = new COL_TYPE(5, "����ʱ��");
}

