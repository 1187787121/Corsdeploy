/**
 * Title: EXE_STATUS.java
 * File Description: ִ��״̬
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ִ��״̬
 * @author AutoGen
 */
public class EXE_STATUS extends EnumValue<EXE_STATUS> {

	private static final long serialVersionUID = -8108246828912416154L;

	private EXE_STATUS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * ��ִ��
	  */
	 public static final EXE_STATUS PENDING = new EXE_STATUS(1, "��ִ��");

	 /**
	  * ִ����
	  */
	 public static final EXE_STATUS RUNNING = new EXE_STATUS(2, "ִ����");

	 /**
	  * ��ִ��
	  */
	 public static final EXE_STATUS EXECUTED = new EXE_STATUS(3, "��ִ��");
	 
	 /**
	  * ����
	  */
	 public static final EXE_STATUS SKIP = new EXE_STATUS(4, "����");
}
