package com.wk.cd.module1.bean;

import com.wk.cd.enu.PROTOCOL_TYPE;

public class SourceListBean {
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
	 * soc_ip
	 */
	private String soc_ip;
	
	public static final String SOC_IPCN = "Э������";

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

	/**
	 * @return soc_ip
	 */
	public String getSoc_ip() {
		return soc_ip;
	}

	/**
	 * @param soc_ip
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
	}
	
}
