/**
 * Title: PageQueryWorkStateInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lijie
 * @version: 1.0
 * @date: 2015-3-30
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.QUERY_TYPE;

/**
 * Class Description: ��ҳ��ѯ����״̬������ӿ�
 * @author lijie
 */
public class PageQueryWorkStateInputBean extends PageQueryActionRootInputBean {
	private static final long serialVersionUID = -3275196528610099547L;

	/**
	 * ��ѯ����
	 */
	private QUERY_TYPE query_type;

	public static final String QUERY_TYPECN = "��ѯ����";

	/**
	 * @return query_type ��ѯ����
	 */
	public QUERY_TYPE getQuery_type() {
		return query_type;
	}

	/**
	 * @param query_type ��ѯ����
	 */
	public void setQuery_type(QUERY_TYPE query_type) {
		this.query_type = query_type;
	}

	
}
