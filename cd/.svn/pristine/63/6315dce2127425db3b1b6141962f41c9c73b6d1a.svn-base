/**
 * Title: TpCompQuoteInfo.java
 * File Description: 组件组件组引用表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-18
 */

package com.wk.cd.module1.info;

import java.io.Serializable;

import com.wk.cd.enu.MODULE_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;

/**
 * Class description:组件组件组引用表
 * @author AutoGen
 */
@Table("MO_COMPONENT_QUOTE")
@PrimaryKey({ "component_id", "quote_id" })
public class MoComponentQuoteInfo
		implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 表名称
	 */
	public static final String TABLECN = "组件组件组引用表";

	/**
	 * 组件ID
	 */
	private String component_id;

	public static final String COMPONENT_IDCN = "组件ID";

	/**
	 * 引用者ID
	 */
	private String quote_id;

	public static final String QUOTE_IDCN = "引用者ID";

	/**
	 * 引用者类型
	 */
	private MODULE_TYPE quote_module_type;

	public static final String QUOTE_MODULE_TYPECN = "引用者类型";

	/**
	 * @return component_id 组件ID
	 */
	public String getComponent_id() {
		return this.component_id;
	}

	/**
	 * @param component_id 组件ID
	 */
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	/**
	 * @return quote_id 引用者ID
	 */
	public String getQuote_id() {
		return this.quote_id;
	}

	/**
	 * @param quote_id 引用者ID
	 */
	public void setQuote_id(String quote_id) {
		this.quote_id = quote_id;
	}

	/**
	 * @return quote_module_type 引用者类型
	 */
	public MODULE_TYPE getQuote_module_type() {
		return this.quote_module_type;
	}

	/**
	 * @param quote_module_type 引用者类型
	 */
	public void setQuote_module_type(MODULE_TYPE quote_module_type) {
		this.quote_module_type = quote_module_type;
	}
	
}
