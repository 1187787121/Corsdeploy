/**
 * Title: COMPONENT_SOURCE.java
 * File Description: �ű���Դ
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-4-6
 */
package com.wk.cd.module1.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �ű���Դ
 * @author AutoGen
 */
public class COMPONENT_SOURCE
		extends EnumValue<COMPONENT_SOURCE> {
	/**
	 * @Fields serialVersionUID : -8021807128623699310L
	 */
	private static final long serialVersionUID = -8021807128623699310L;

	private COMPONENT_SOURCE(int value, String name) {
		super(value, name);
	}

	/**
	 * ����
	 */
	public static final COMPONENT_SOURCE INPUT = new COMPONENT_SOURCE(1, "����");

	/**
	 * �ļ�
	 */
	public static final COMPONENT_SOURCE FILE = new COMPONENT_SOURCE(2, "�ļ�");

}
