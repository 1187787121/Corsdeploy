/**
 * Title: AF_FLAG.java.java
 * File Description: �Ƿ��ϼ�����
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �Ƿ��ϼ�����
 * @author AutoGen
 */
public class SUPER_FLAG
		extends EnumValue<SUPER_FLAG> {

	private static final long serialVersionUID = -6091356132468866071L;

	/**
	 * ���캯��
	 */
	private SUPER_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "�Ƿ��ϼ�����";

	/**
	 * 1 ��
	 */
	public static final SUPER_FLAG YES = new SUPER_FLAG(1, "��");

	/**
	 * 2 ��
	 */
	public static final SUPER_FLAG NO = new SUPER_FLAG(2, "��");
}
