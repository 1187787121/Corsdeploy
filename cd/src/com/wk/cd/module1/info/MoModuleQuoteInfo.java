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
@Table("MO_MODULE_QUOTE")
@PrimaryKey({ "module_id", "quote_module_id" })
public class MoModuleQuoteInfo
		implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 表名称
	 */
	public static final String TABLECN = "组件组件组引用表";

	/**
	 * 组件ID
	 */
	private String module_id;

	public static final String MODULE_IDCN = "组件ID";

	/**
	 * 引用组件ID
	 */
	private String quote_module_id;

	public static final String QUOTE_MODULE_IDCN = "引用组件ID";

	/**
	 * 引用组件类型
	 */
	private MODULE_TYPE quote_module_type;

	public static final String QUOTE_MODULE_TYPECN = "引用组件类型";

	/**
	 * @return module_id 组件ID
	 */
	public String getModule_id() {
		return this.module_id;
	}

	/**
	 * @param module_id 组件ID
	 */
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	/**
	 * @return quote_module_id 引用组件ID
	 */
	public String getQuote_module_id() {
		return this.quote_module_id;
	}

	/**
	 * @param quote_module_id 引用组件ID
	 */
	public void setQuote_module_id(String quote_module_id) {
		this.quote_module_id = quote_module_id;
	}

	/**
	 * @return quote_module_type 引用组件类型
	 */
	public MODULE_TYPE getQuote_module_type() {
		return this.quote_module_type;
	}

	/**
	 * @param quote_module_type 引用组件类型
	 */
	public void setQuote_module_type(MODULE_TYPE quote_module_type) {
		this.quote_module_type = quote_module_type;
	}
	
}
