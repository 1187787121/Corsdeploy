/**
 * Title: STORAGE_STATUS.java
 * File Description: 入库状态
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 入库状态
 * @author AutoGen
 */
public class STORAGE_STATUS extends EnumValue<STORAGE_STATUS> {

	private static final long serialVersionUID = 3581891867617234886L;

	private STORAGE_STATUS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 待入库
	  */
	 public static final STORAGE_STATUS PENDING = new STORAGE_STATUS(1, "待入库");

	 /**
	  * 入库中
	  */
	 public static final STORAGE_STATUS STORAGING = new STORAGE_STATUS(2, "入库中");

	 /**
	  * 已入库
	  */
	 public static final STORAGE_STATUS STORAGED = new STORAGE_STATUS(3, "已入库");
	 
	 /**
	  * 关闭
	  */
	 public static final STORAGE_STATUS CLOSE = new STORAGE_STATUS(4, "关闭");


}
