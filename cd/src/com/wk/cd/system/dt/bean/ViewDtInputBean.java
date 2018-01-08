/**
 * Title: PageAllSocInputBean.java
 * File Description: ��ҳ��ѯ��������Դ��Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:��ҳ��ѯ��������Դ��Ϣ����ӿ�
 * @author link
 */
public class ViewDtInputBean
		extends ActionRootInputBean {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -4706304835406156753L;
	
	/**
	 *IP��ַ
	 */
	private String soc_ip;

	public static final String SOC_IPCN = "IP��ַ";
	
	/**
	 * �ڵ���
	 */
	private String node_name;

	public static final String NODE_NAMECN = "�ڵ���";
	
	/**
	 *����Դ����
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ����";
	
	/**
	 *@return soc_ip IP��ַ
	 */
	public String getSoc_ip() {
		return this.soc_ip;
	}

	/**
	 *@param soc_ip IP��ַ
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
	}

	/**
	 * @return node_name �ڵ���
	 */
	public String getNode_name() {
		return node_name;
	}

	/**
	 * @param node_name �ڵ���
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

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

}
