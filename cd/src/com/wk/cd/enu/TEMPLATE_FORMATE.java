/**
 * Title: TEMPLATE_FORMATE.java
 * File Description: ģ���ʽ
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-26
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ģ���ʽ
 * @author AutoGen
 */
public class TEMPLATE_FORMATE extends EnumValue<TEMPLATE_FORMATE> {
	 /** 
	 * @Fields serialVersionUID : 8970555143147233003L
	 */ 
	private static final long serialVersionUID = 8970555143147233003L;

	private TEMPLATE_FORMATE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * EXCEL�ļ�
	  */
	 public static final TEMPLATE_FORMATE EXCEL = new TEMPLATE_FORMATE(1, "EXCEL�ļ�");

	 /**
	  * XML�ļ�
	  */
	 public static final TEMPLATE_FORMATE XML = new TEMPLATE_FORMATE(2, "XML�ļ�");

}
