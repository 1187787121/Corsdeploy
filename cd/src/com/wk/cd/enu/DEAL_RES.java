/**
 * Title: DEAL_RES.java
 * File Description: 处理结果
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 处理结果
 * @author AutoGen
 */
public class DEAL_RES
		extends EnumValue<DEAL_RES> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5397163820478993696L;

	/**
	 * 构造函数
	 */
	private DEAL_RES(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "处理结果";

	/**
	 * 1 同意
	 */
	public static final DEAL_RES AGREE = new DEAL_RES(1, "同意");

	/**
	 * 2 拒绝
	 */
	public static final DEAL_RES REFUSE = new DEAL_RES(2, "拒绝");

	/**
	 * 3 无关
	 */
	public static final DEAL_RES NOTHING = new DEAL_RES(3, "无关");
	
	/**
	 * 4 关闭
	 */
	public static final DEAL_RES CLOSE = new DEAL_RES(4, "关闭");
	
}
