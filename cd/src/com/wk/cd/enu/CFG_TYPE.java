/**
 * Title: CFG_TYPE.java
 * File Description: ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ��������
 * @author AutoGen
 */
public class CFG_TYPE extends EnumValue<CFG_TYPE> {
	
	private static final long serialVersionUID = 7841631063783698139L;

	private CFG_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * һ������
	  */
	 public static final CFG_TYPE NORMAL = new CFG_TYPE(1, "һ������");

	 /**
	  * tomcat����
	  */
	 public static final CFG_TYPE TOMCAT = new CFG_TYPE(2, "tomcat����");

}
