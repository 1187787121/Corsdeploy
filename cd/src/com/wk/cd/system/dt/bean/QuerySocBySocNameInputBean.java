/**
 * Title: QuerySocBySocNameInputBean.java
 * File Description: ��������Դ���Ʋ�ѯ����Դ��ϸ��Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-11
 */
package com.wk.cd.system.dt.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��������Դ���Ʋ�ѯ����Դ��ϸ��Ϣ����ӿ�
 * @author link
 */
public class QuerySocBySocNameInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 5579416542460445781L;
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
