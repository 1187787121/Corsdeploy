/**
 * Title: QueryUserTermListOutputBean.java
 * File Description: ��ѯ�û������ն��б�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��14��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: ��ѯ�û������ն��б�����ӿ�
 * @author HT
 */
public class PageUserTermOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = -5775343308098448137L;
	
	/**
	 * �ն��б�
	 */
	private List<UsUserTermBean> term_list;
	
	public final static String TERM_LISTCN="�ն��б�";

	/**
	 * @return term_list �ն��б�
	 */
	public List<UsUserTermBean> getTerm_list() {
		return this.term_list;
	}

	/**
	 * @param term_list �ն��б�
	 */
	public void setTerm_list(List<UsUserTermBean> term_list) {
		this.term_list = term_list;
	}
}
