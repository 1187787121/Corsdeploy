/**
 * Title: COMPONENT_PURPOSE.java
 * File Description: �����;
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-4-6
 */
package com.wk.cd.module1.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: �����;
 * @author AutoGen
 */
public class COMPONENT_PURPOSE extends EnumValue<COMPONENT_PURPOSE> {
	 /** 
	 * @Fields serialVersionUID : 7557506646574530419L
	 */ 
	private static final long serialVersionUID = 7557506646574530419L;

	private COMPONENT_PURPOSE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * Ӧ�÷���
	  */
	 public static final COMPONENT_PURPOSE PROD = new COMPONENT_PURPOSE(1, "Ӧ�÷���");

	 /**
	  * ��־Ѳ��
	  */
	 public static final COMPONENT_PURPOSE COLLECT = new COMPONENT_PURPOSE(2, "��־Ѳ��");
	 
	 /**
	  * ��ҵ����
	  */
	 public static final COMPONENT_PURPOSE OPERATION = new COMPONENT_PURPOSE(3, "��ҵ����");
	 
	 /**
	  * ����ά��
	  */
	 public static final COMPONENT_PURPOSE PROBLEM = new COMPONENT_PURPOSE(4, "����ά��");
	 
	 /**
	  * ������֤
	  */
	 public static final COMPONENT_PURPOSE CHECK = new COMPONENT_PURPOSE(5, "������֤");

}
