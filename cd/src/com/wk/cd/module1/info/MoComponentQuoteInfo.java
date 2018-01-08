/**
 * Title: TpCompQuoteInfo.java
 * File Description: �����������ñ�
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
 * Class description:�����������ñ�
 * @author AutoGen
 */
@Table("MO_COMPONENT_QUOTE")
@PrimaryKey({ "component_id", "quote_id" })
public class MoComponentQuoteInfo
		implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ������
	 */
	public static final String TABLECN = "�����������ñ�";

	/**
	 * ���ID
	 */
	private String component_id;

	public static final String COMPONENT_IDCN = "���ID";

	/**
	 * ������ID
	 */
	private String quote_id;

	public static final String QUOTE_IDCN = "������ID";

	/**
	 * ����������
	 */
	private MODULE_TYPE quote_module_type;

	public static final String QUOTE_MODULE_TYPECN = "����������";

	/**
	 * @return component_id ���ID
	 */
	public String getComponent_id() {
		return this.component_id;
	}

	/**
	 * @param component_id ���ID
	 */
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	/**
	 * @return quote_id ������ID
	 */
	public String getQuote_id() {
		return this.quote_id;
	}

	/**
	 * @param quote_id ������ID
	 */
	public void setQuote_id(String quote_id) {
		this.quote_id = quote_id;
	}

	/**
	 * @return quote_module_type ����������
	 */
	public MODULE_TYPE getQuote_module_type() {
		return this.quote_module_type;
	}

	/**
	 * @param quote_module_type ����������
	 */
	public void setQuote_module_type(MODULE_TYPE quote_module_type) {
		this.quote_module_type = quote_module_type;
	}
	
}
