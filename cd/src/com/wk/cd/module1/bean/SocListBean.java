/**
 * Title: SocBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2017��8��26��
 */
package com.wk.cd.module1.bean;

import com.wk.cd.enu.PROTOCOL_TYPE;

/**
 * Class Description: 
 * @author chiss
 */
public class SocListBean {
	/**
	 *����Դ����
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ����";
	/**
	 *Э������
	 */
	private PROTOCOL_TYPE protocol_type;

	public static final String PROTOCOL_TYPECN = "Э������";

	/**
	 * @return soc_name
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return protocol_type
	 */
	public PROTOCOL_TYPE getProtocol_type() {
		return protocol_type;
	}

	/**
	 * @param protocol_type
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}
	
	
}
