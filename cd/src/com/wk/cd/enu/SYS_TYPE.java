/**
 * Title: SYS_TYPE.java
 * File Description: Ӧ��ϵͳ����
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: Ӧ��ϵͳ����
 * @author AutoGen
 */
public class SYS_TYPE extends EnumValue<SYS_TYPE> {
	 /** 
	 * @Fields serialVersionUID : -7870324938755166064L
	 */ 
	private static final long serialVersionUID = -7870324938755166064L;

	private SYS_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ����
	  */
	 public static final SYS_TYPE CHANNEL = new SYS_TYPE(1, "����");

	 /**
	  * ��̨
	  */
	 public static final SYS_TYPE MIDDLEGD = new SYS_TYPE(2, "��̨");

	 /**
	  * ESB
	  */
	 public static final SYS_TYPE ESB = new SYS_TYPE(3, "ESB");

	 /**
	  * ��ͳҵ��
	  */
	 public static final SYS_TYPE TRADITIONAL = new SYS_TYPE(4, "��ͳҵ��");

	 /**
	  * ������
	  */
	 public static final SYS_TYPE INTERNET = new SYS_TYPE(5, "������");
	 
	 /**
	  * ǰ��
	  */
	 public static final SYS_TYPE PREPOSE = new SYS_TYPE(6, "ǰ��");

}
