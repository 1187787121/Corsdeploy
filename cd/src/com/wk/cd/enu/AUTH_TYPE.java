/**
 * Title: AUTH_TYPE.java
 * File Description: ��Ȩ����
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��Ȩ����
 * @author AutoGen
 */
public class AUTH_TYPE
		extends EnumValue<AUTH_TYPE> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 575191555126861069L;

	/**
	 * ���캯��
	 */
	private AUTH_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "��Ȩ����";

	/**
	 * 1 ������Ȩ
	 */
	public static final AUTH_TYPE LOCAL = new AUTH_TYPE(1, "������Ȩ");

	/**
	 * 2 Զ����Ȩ
	 */
	public static final AUTH_TYPE REMOTE = new AUTH_TYPE(2, "Զ����Ȩ");
}
