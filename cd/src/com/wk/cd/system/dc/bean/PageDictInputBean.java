/**
 * Title: PageDictInputBean.java
 * File Description: ��ҳ��ѯ�����ֵ��б�����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014��11��17��
 */
package com.wk.cd.system.dc.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description:��ҳ��ѯ�����ֵ��б�����ӿ���
 * @author HT
 */
public class PageDictInputBean
		extends PageQueryActionRootInputBean {
	
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 9021558651547699863L;
	/**
	 * �ؼ���
	 */
	private String keyword; 
	public static final String KEYWORDCN="�ؼ���";
	/**
	 * @return keyword �ؼ���
	 */
	public String getKeyword() {
		return this.keyword;
	}
	/**
	 * @param keyword �ؼ���
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
