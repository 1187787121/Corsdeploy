/**
 * Title: DT_RANGE.java
 * File Description: ���ݷ�Χ
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: ���ݷ�Χ
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
	  * ȫ������
	  */
	 public static final DT_RANGE ALL = new DT_RANGE(1, "ȫ������");

	 /**
	  * ��������
	  */
	 public static final DT_RANGE PART = new DT_RANGE(2, "��������");

}
