/**
 * Title: PageInteTaskOutputBean.java
 * File Description: ��ҳ��ѯ���������б�����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: ��ҳ��ѯ���������б�����ӿ�
 * @author Xul
 */
public class PageInteTaskOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = -6290838741369996274L;
	
	/**
	 * ���������б�
	 */
	private List<InteTaskBean> inte_list;
	
	public static final String INTE_LISTCN = "���������б�";

	/**
	 * @return inte_list ���������б�
	 */
	public List<InteTaskBean> getInte_list() {
		return inte_list;
	}

	/**
	 * @param inte_list ���������б�
	 */
	public void setInte_list(List<InteTaskBean> inte_list) {
		this.inte_list = inte_list;
	}
}
