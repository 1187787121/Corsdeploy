/**
 * Title: SocBean.java
 * File Description: ����Դ����Bean
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��1��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.enu.PROTOCOL_TYPE;

/**
 * Class Description: ����Դ����Bean
 * @author yangl
 */
public class SocBean {

	/**
	 * ����Դ����
	 */
	private String soc_name;
	public static final String SOC_NAME_CN = "���ݿ�";

	/**
	 * ����ԴЭ������
	 */
	private PROTOCOL_TYPE protocol_type;
	public static final String PROTOCOL_TYPE_CN = "���ݿ�汾��";

	/**
	 * �û���·��
	 */
	private String user_root_path;

	public static final String USER_ROOT_PATHCN = "�û���·��";

	/**
	 * @return soc_name ����Դ����
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return protocol_type ����ԴЭ������
	 */
	public PROTOCOL_TYPE getProtocol_type() {
		return protocol_type;
	}

	/**
	 * @param protocol_type ����ԴЭ������
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * @return user_root_path �û���·��
	 */
	public String getUser_root_path() {
		return user_root_path;
	}

	/**
	 * @param user_root_path �û���·��
	 */
	public void setUser_root_path(String user_root_path) {
		this.user_root_path = user_root_path;
	}

}
