/**
 * Title: PHASE_TYPE.java
 * File Description: �׶�����
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �׶�����
 * @author AutoGen
 */
public class PHASE_TYPE
		extends EnumValue<PHASE_TYPE> {

	private static final long serialVersionUID = -6091356132468866071L;

	/**
	 * ���캯��
	 */
	private PHASE_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "�׶�����";

	/**
	 * 1 ���
	 */
	public static final PHASE_TYPE COMPONENT = new PHASE_TYPE(1, "���");

	/**
	 * 2 ��֤
	 */
	public static final PHASE_TYPE CHECK = new PHASE_TYPE(2, "��֤");
	
	/**
	 * 3 �ű�
	 */
	public static final PHASE_TYPE SCRIPT = new PHASE_TYPE(3, "�ű�");
	
	/**
	 * 4 �̻�����
	 */
	public static final PHASE_TYPE CONFIG = new PHASE_TYPE(4, "�̻�����");
	
	/**
	 * 5 �Զ�������
	 */
	public static final PHASE_TYPE FREECONFIG = new PHASE_TYPE(5, "�Զ�������");
	
	/**
	 * 6 �˹�
	 */
	public static final PHASE_TYPE MANUAL = new PHASE_TYPE(6, "�˹�");
	
}
