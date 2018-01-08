/**
 * Title: OPT_STATUS.java
 * File Description: ����״̬
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ����״̬
 * @author AutoGen
 */
public class OPT_STATUS extends EnumValue<OPT_STATUS> {

	private static final long serialVersionUID = -8914581095198172886L;

	private OPT_STATUS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ���޸�
	  */
	 public static final OPT_STATUS PENDING = new OPT_STATUS(1, "���޸�");

	 /**
	  * �޸ĳɹ�
	  */
	 public static final OPT_STATUS SUCCESS = new OPT_STATUS(2, "�޸ĳɹ�");

	 /**
	  * �޸�ʧ��
	  */
	 public static final OPT_STATUS FAIL = new OPT_STATUS(3, "�޸�ʧ��");

}
