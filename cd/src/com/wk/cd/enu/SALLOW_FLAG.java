/**
 * Title: SALLOW_FLAG.java
 * File Description: ����ִ�б�־
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-5
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description:����ִ�б�־
 * @author AutoGen
 */
public class SALLOW_FLAG
		extends EnumValue<SALLOW_FLAG> {
	private static final long serialVersionUID = 8443577769507140564L;

	private SALLOW_FLAG(int value, String name) {
		super(value, name);
	}

	/**
	 * ����ִ��
	 */
	public static final SALLOW_FLAG ALWLOCAL = new SALLOW_FLAG(1, "����ִ��");

	/**
	 * Զ��ִ��
	 */
	public static final SALLOW_FLAG ALWREMOTE = new SALLOW_FLAG(2, "Զ��ִ��");

	/**
	 * ������
	 */
	public static final SALLOW_FLAG ALL = new SALLOW_FLAG(3, "������");

}
