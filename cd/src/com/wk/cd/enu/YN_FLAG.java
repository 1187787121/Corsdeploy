/**
 * Title: YN_FLAG.java
 * File Description: �Ƿ��־
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �Ƿ��־
 * @author AutoGen
 */
public class YN_FLAG
		extends EnumValue<YN_FLAG> {

	private static final long serialVersionUID = -6758581760111554391L;

	/**
	 * ���캯��
	 */
	private YN_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "�Ƿ��־";

	/**
	 * 1 ��
	 */
	public static final YN_FLAG YES = new YN_FLAG(1, "��");

	/**
	 * 2 ��
	 */
	public static final YN_FLAG NO = new YN_FLAG(2, "��");
}
