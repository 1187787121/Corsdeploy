/**
 * Title: RCD_STATE.java
 * File Description: ��¼״̬
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��¼״̬
 * @author AutoGen
 */
public class RCD_STATE
		extends EnumValue<RCD_STATE> {

	private static final long serialVersionUID = 5668308199786056289L;

	/**
	 * ���캯��
	 */
	private RCD_STATE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "��¼״̬";

	/**
	 * 1 ����
	 */
	public static final RCD_STATE NORMAL = new RCD_STATE(1, "����");

	/**
	 * 2 ɾ��
	 */
	public static final RCD_STATE ABNORMAL = new RCD_STATE(2, "ɾ��");
}
