/**
 * Title: PRIV_FLAG.java
 * File Description: Ȩ��
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: Ȩ��
 * @author AutoGen
 */
public class PRIV_FLAG
		extends EnumValue<PRIV_FLAG> {

	private static final long serialVersionUID = 7294383168135513675L;

	/**
	 * ���캯��
	 */
	private PRIV_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "Ȩ��";

	/**
	 * 1 ��
	 */
	public static final PRIV_FLAG YES = new PRIV_FLAG(1, "��");

	/**
	 * 2 ��
	 */
	public static final PRIV_FLAG NO = new PRIV_FLAG(2, "��");
}
