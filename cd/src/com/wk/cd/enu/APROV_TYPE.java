/**
 * Title: APROV_TYPE.java
 * File Description: ����չʾ����
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-15
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ����չʾ����
 * @author AutoGen
 */
public class APROV_TYPE extends EnumValue<APROV_TYPE> {

	private static final long serialVersionUID = -8184602612800041913L;
	
	/**
	 * ���캯��
	 */
	private APROV_TYPE(int value, String name) {
		 super(value, name);
	 }
	
	public static final String ENUMCN = "����չʾ����";
	
	 /**
	  * 1 ���ӿ�
	  */
	 public static final APROV_TYPE ONLY_INTERFACE = new APROV_TYPE(1, "���ӿ�");

	 /**
	  * 2 ��̬ҳ��
	  */
	 public static final APROV_TYPE STATIC_PAGE = new APROV_TYPE(2, "��̬ҳ��");

	 /**
	  * 3 ����ҳ��
	  */
	 public static final APROV_TYPE CUSTOM_PAGE = new APROV_TYPE(3, "����ҳ��");

}
