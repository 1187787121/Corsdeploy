/**
 * Title: GetDictInputBean.java
 * File Description: ��ѯ�����ֵ���ϸ��Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014��11��17��
 */
package com.wk.cd.system.dc.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯ�����ֵ���ϸ��Ϣ����ӿ���
 * @author HT
 */
public class GetDictInputBean extends ActionRootInputBean{
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = -8628803398600472902L;
	/**
	 *  ������
	 */
	private String domain_name;
	public static final String DOMAIN_NAMECN="������";
	/**
	 * @return domain_name ������
	 */
	public String getDomain_name() {
		return this.domain_name;
	}
	/**
	 * @param domain_name ������
	 */
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	
	
}
