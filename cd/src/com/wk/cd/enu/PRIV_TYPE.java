/**
 * Title: PRIV_TYPE.java
 * File Description: 权限类型
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 权限类型
 * @author AutoGen
 */
public class PRIV_TYPE
		extends EnumValue<PRIV_TYPE> {

	private static final long serialVersionUID = -7282729877608044093L;

	/**
	 * 构造函数
	 */
	private PRIV_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "权限类型";

	/**
	 * 1 永久
	 */
	public static final PRIV_TYPE PERPETUAL = new PRIV_TYPE(1, "永久");

	/**
	 * 2 临时无关
	 */
	public static final PRIV_TYPE TEMPORARY = new PRIV_TYPE(2, "临时");
	
	/**
	 * 2 临时页面
	 */
	public static final PRIV_TYPE TEMPPAGE = new PRIV_TYPE(3, "临时页面");
}
