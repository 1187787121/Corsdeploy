/**
 * Title: PRIV_TYPE.java
 * File Description: Ȩ������
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: Ȩ������
 * @author AutoGen
 */
public class PRIV_TYPE
		extends EnumValue<PRIV_TYPE> {

	private static final long serialVersionUID = -7282729877608044093L;

	/**
	 * ���캯��
	 */
	private PRIV_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "Ȩ������";

	/**
	 * 1 ����
	 */
	public static final PRIV_TYPE PERPETUAL = new PRIV_TYPE(1, "����");

	/**
	 * 2 ��ʱ�޹�
	 */
	public static final PRIV_TYPE TEMPORARY = new PRIV_TYPE(2, "��ʱ");
	
	/**
	 * 2 ��ʱҳ��
	 */
	public static final PRIV_TYPE TEMPPAGE = new PRIV_TYPE(3, "��ʱҳ��");
}
