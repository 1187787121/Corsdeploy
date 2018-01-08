/**
 * Title: APROV_CATEGORY.java
 * File Description: 审批类别
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-03-28
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 审批类别
 * @author AutoGen
 */
public class APROV_CATEGORY
		extends EnumValue<APROV_CATEGORY> {

	private static final long serialVersionUID = -6091356132468866071L;

	/**
	 * 构造函数
	 */
	private APROV_CATEGORY(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "审批类别";

	/**
	 * 1 部门角色
	 */
	public static final APROV_CATEGORY DEPARTMENTROLE = new APROV_CATEGORY(1, "部门角色");

	/**
	 * 2 本部角色
	 */
	public static final APROV_CATEGORY ROLE = new APROV_CATEGORY(2, "本部角色");
}
