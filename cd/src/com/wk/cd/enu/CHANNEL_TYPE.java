/**
 * Title: CHANNEL_TYPE.java
 * File Description: 渠道类型
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 渠道类型
 * @author AutoGen
 */
public class CHANNEL_TYPE extends EnumValue<CHANNEL_TYPE> {

	private static final long serialVersionUID = -3251649253820014560L;

	private CHANNEL_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * PC web接入
	  */
	 public static final CHANNEL_TYPE PCWEB = new CHANNEL_TYPE(1, "PC web接入");

	 /**
	  * Andriod APP接入
	  */
	 public static final CHANNEL_TYPE ANDRIOD = new CHANNEL_TYPE(2, "Andriod APP接入");

	 /**
	  * IOS APP接入
	  */
	 public static final CHANNEL_TYPE IOS = new CHANNEL_TYPE(3, "IOS APP接入");

	 /**
	  * IPad APP 接入
	  */
	 public static final CHANNEL_TYPE IPAD = new CHANNEL_TYPE(4, "IPad APP 接入");

	 /**
	  * Email
	  */
	 public static final CHANNEL_TYPE EMAIL = new CHANNEL_TYPE(5, "Email");

	 /**
	  * 微信
	  */
	 public static final CHANNEL_TYPE WX = new CHANNEL_TYPE(6, "微信");

}
