/**
 * Title: DEAL_RES.java
 * File Description: ������
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ������
 * @author AutoGen
 */
public class DEAL_RES
		extends EnumValue<DEAL_RES> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5397163820478993696L;

	/**
	 * ���캯��
	 */
	private DEAL_RES(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "������";

	/**
	 * 1 ͬ��
	 */
	public static final DEAL_RES AGREE = new DEAL_RES(1, "ͬ��");

	/**
	 * 2 �ܾ�
	 */
	public static final DEAL_RES REFUSE = new DEAL_RES(2, "�ܾ�");

	/**
	 * 3 �޹�
	 */
	public static final DEAL_RES NOTHING = new DEAL_RES(3, "�޹�");
	
	/**
	 * 4 �ر�
	 */
	public static final DEAL_RES CLOSE = new DEAL_RES(4, "�ر�");
	
}
