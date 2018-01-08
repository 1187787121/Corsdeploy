/**
 * Title: DT_RANGE.java
 * File Description: 数据范围
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 数据范围
 * @author AutoGen
 */
public class DT_RANGE extends EnumValue<DT_RANGE> {
	 /**
	 * 1862037171850366245L
	 */
	private static final long serialVersionUID = 1862037171850366245L;

	private DT_RANGE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 全量数据
	  */
	 public static final DT_RANGE ALL = new DT_RANGE(1, "全量数据");

	 /**
	  * 部分数据
	  */
	 public static final DT_RANGE PART = new DT_RANGE(2, "部分数据");

}
