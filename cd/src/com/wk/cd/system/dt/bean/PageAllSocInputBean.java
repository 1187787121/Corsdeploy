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

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description:��ҳ��ѯ��������Դ��Ϣ����ӿ�
 * @author link
 */
public class PageAllSocInputBean
		extends PageQueryActionRootInputBean {

	private static final long serialVersionUID = -3943098295949353270L;
	private String soc_name;
	public static final String SOC_NAMECN = "����Դ����";

	/**
	 * @return soc_name ����Դ����
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 * @param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

}
