/**
 * Title: TASK_TYPE.java
 * File Description: ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-19
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��������
 * @author AutoGen
 */
public class TASK_TYPE extends EnumValue<TASK_TYPE> {

	private static final long serialVersionUID = -8354608393723141360L;

	private TASK_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ����
	  */
	 public static final TASK_TYPE BUILD = new TASK_TYPE(1, "����");

	 /**
	  * ����
	  */
	 public static final TASK_TYPE INTEGRATION = new TASK_TYPE(2, "����");

	 /**
	  * ����
	  */
	 public static final TASK_TYPE PUBLISH = new TASK_TYPE(3, "����");

	 /**
	  * ��������
	  */
	 public static final TASK_TYPE PUBLISHROLL = new TASK_TYPE(4, "��������");

}
