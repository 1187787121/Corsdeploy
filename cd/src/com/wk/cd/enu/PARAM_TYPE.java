/**
 * Title: PARAM_TYPE.java
 * File Description: ģ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-5-30
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ģ������
 * @author AutoGen
 */
public class PARAM_TYPE extends EnumValue<PARAM_TYPE> {
	
	private static final long serialVersionUID = -4856609582387935456L;

	private PARAM_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ��Ŀ����
	  */
	 public static final PARAM_TYPE PJPARAM = new PARAM_TYPE(1, "��Ŀ����");

	 /**
	  * Ͷ��������
	  */
	 public static final PARAM_TYPE PDDPARAM = new PARAM_TYPE(2, "Ͷ��������");

}
