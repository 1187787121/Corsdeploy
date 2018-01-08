/**
 * Title: CVT_TYPE.java
 * File Description: ת�뷽ʽ
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ת�뷽ʽ
 * @author AutoGen
 */
public class CVT_TYPE
		extends EnumValue<CVT_TYPE> {

	private static final long serialVersionUID = 303467450903463971L;

	/**
	 * ���캯��
	 */
	private CVT_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "ת�뷽ʽ";

	/**
	 * 1 ��ת��
	 */
	public static final CVT_TYPE NOCVT = new CVT_TYPE(1, "��ת��");

	/**
	 * 2 GBK/EBCDICƽ̨��ת
	 */
	public static final CVT_TYPE PLTCVT = new CVT_TYPE(2, "GBK/EBCDICƽ̨��ת");

	/**
	 * 3 GBK/EBCDIC�ű���ת
	 */
	public static final CVT_TYPE SCPCVT = new CVT_TYPE(3, "GBK/EBCDIC�ű���ת");

	/**
	 * 4 FTP�Զ�ת��
	 */
	public static final CVT_TYPE FTPCVT = new CVT_TYPE(4, "FTP�Զ�ת��");
}
