/**
 * Title: PagePublishTaskOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 
 * @author chiss
 */
public class PagePublishTaskOutputBean extends PageQueryActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3881533410137040551L;
	
	/**
	 * ���������б�
	 */
	private List<PubTaskBean> pub_list;
	
	public static final String PUB_LISTCN = "���������б�";

	/**
	 * @return pub_list ���������б�
	 */
	public List<PubTaskBean> getPub_list() {
		return pub_list;
	}

	/**
	 * @param pub_list ���������б�
	 */
	public void setPub_list(List<PubTaskBean> pub_list) {
		this.pub_list = pub_list;
	}
	
}
