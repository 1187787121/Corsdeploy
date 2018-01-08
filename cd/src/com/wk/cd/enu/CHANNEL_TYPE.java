/**
 * Title: CHANNEL_TYPE.java
 * File Description: ��������
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��������
 * @author AutoGen
 */
public class CHANNEL_TYPE extends EnumValue<CHANNEL_TYPE> {

	private static final long serialVersionUID = -3251649253820014560L;

	private CHANNEL_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * PC web����
	  */
	 public static final CHANNEL_TYPE PCWEB = new CHANNEL_TYPE(1, "PC web����");

	 /**
	  * Andriod APP����
	  */
	 public static final CHANNEL_TYPE ANDRIOD = new CHANNEL_TYPE(2, "Andriod APP����");

	 /**
	  * IOS APP����
	  */
	 public static final CHANNEL_TYPE IOS = new CHANNEL_TYPE(3, "IOS APP����");

	 /**
	  * IPad APP ����
	  */
	 public static final CHANNEL_TYPE IPAD = new CHANNEL_TYPE(4, "IPad APP ����");

	 /**
	  * Email
	  */
	 public static final CHANNEL_TYPE EMAIL = new CHANNEL_TYPE(5, "Email");

	 /**
	  * ΢��
	  */
	 public static final CHANNEL_TYPE WX = new CHANNEL_TYPE(6, "΢��");

}
