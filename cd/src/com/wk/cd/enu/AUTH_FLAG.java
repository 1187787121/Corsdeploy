/**
 * Title: AUTH_FLAG.java
 * File Description: �Ƿ�������Ȩ
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �Ƿ�������Ȩ
 * @author AutoGen
 */
public class AUTH_FLAG
		extends EnumValue<AUTH_FLAG> {

	private static final long serialVersionUID = 8734548354086386573L;

	/**
	 * ���캯��
	 */
	private AUTH_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "�Ƿ�������Ȩ";

	/**
	 * 1 ����
	 */
	public static final AUTH_FLAG ALLOW = new AUTH_FLAG(1, "����");

	/**
	 * 2 ��ֹ
	 */
	public static final AUTH_FLAG FORBID = new AUTH_FLAG(2, "��ֹ");
}
